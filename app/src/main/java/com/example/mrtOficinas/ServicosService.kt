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
        var servicos = ArrayList<Servicos>()
        if (AndroidUtils.isInternetDisponivel(context)){
            val url ="$host/servicos"
            val json = HttpHelper.get(url)
            servicos = parserJson(json)

            for (d in servicos){
                saveOffline(d)
            }
            return servicos
        } else{
            var dao = DatabaseManager.getServicoDAO()
            val servicos:List<Servicos> = dao.findAll()
            return servicos
        }
    }

    fun getServicos(context: Context,id:Long): Servicos?{
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/servicos/${id}"
            val json = HttpHelper.get(url)
            val servicos = parserJson<Servicos>(json)

            return servicos
        } else {
            val dao = DatabaseManager.getServicoDAO()
            val servicos = dao.getById(id)
            return servicos
        }

    }
    fun save(context: Context,servicos: Servicos): Response {
        if (AndroidUtils.isInternetDisponivel(context)) {
            val json = HttpHelper.post("$host/servicos", servicos.toJson())
            return parserJson(json)
        }
        else {
            saveOffline(servicos)
            return Response("OK", "Serviço salvo no dispositivo")
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

    fun delete(context: Context,servicos: Servicos): Response {
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/servicos/${servicos.id}"
            val json = HttpHelper.delete(url)

            return parserJson(json)
        } else {
            val dao = DatabaseManager.getServicoDAO()
            dao.delete(servicos)
            return Response(status = "OK", msg = "Dados salvos localmente")
        }
    }

        inline fun <reified T> parserJson(json: String): T {
            val type = object : TypeToken<T>(){}.type
            return Gson().fromJson<T>(json, type)
        }
}