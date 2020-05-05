package com.example.mrtOficinas

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.room.Database
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URL

object ServicosService {
    val host = "https://mrtoficinas.pythonanywhere.com"
    val TAG = "WS_MRTOficinas"
    fun getServicos(context: Context): List<Servicos>{
        if (AndroidUtils.isInternetDisponivel(context)){
            val url ="$host/servicos"
            val json = HttpHelper.get(url)
            var servicos = parserJson<List<Servicos>>(json)

            for (d in servicos){
                saveOffline(d)
            }
            return servicos
            Log.d(TAG, json)

            return parserJson<List<Servicos>>(json)
        }
        else{
            var dao = DatabaseManager.getServicoDAO()
            return dao.findAll()
        }
    }
    fun saveOffline(servicos: Servicos): Boolean{
        val dao = DatabaseManager.getServicoDAO()

        if(!existeServico(servicos)){
            dao.insert(servicos)
        }
        return true
    }

    fun existeServico(servicos: Servicos):Boolean{
        val dao = DatabaseManager.getServicoDAO()
        return dao.getById(servicos.id) !=null
    }

    fun save(servicos: Servicos): Response {
        val json = HttpHelper.post("$host/servicos", servicos.toJson())
        return parserJson(json)
    }

    fun delete(servicos: Servicos): Response {
        Log.d(TAG, servicos.id.toString())
        val url = "$host/servicos/${servicos.id}"
        val json = HttpHelper.delete(url)
        Log.d(TAG, json)
        return parserJson(json)
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)

    }
}