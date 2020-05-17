package com.example.mrtOficinas

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object LoginService {

    val host = "https://mrtoficinas.pythonanywhere.com"
    val TAG = "WS_MRTOficinas_LOGIN"

    fun getLogin(context: Context): List<Login>{
        var login = ArrayList<Login>()
        if (AndroidUtils.isInternetDisponivel(context)){
            val url ="${LoginService.host}/users"
            val json = HttpHelper.get(url)
            login = LoginService.parserJson(json)

            for (d in login){
                saveOffline(d)
            }
            return login
        } else{
            var dao = DatabaseManager.getLoginDAO()
            val login:List<Login> = dao.findAllLogin()
            return login
        }
    }


    fun save(context: Context,login: Login): Response {
        if (AndroidUtils.isInternetDisponivel(context)) {
            val json = HttpHelper.post("$host/users", login.toJson())
            return parserJson(json)
        }
        else {
            saveOffline(login)
            return Response("OK", "Login salvo no dispositivo")
        }
    }
    fun saveOffline(login: Login): Boolean{
        val dao = DatabaseManager.getLoginDAO()
        dao.insert(login)
        return true
    }

    fun save(login: Login): Response {
        val json = HttpHelper.post("${LoginService.host}/users", login.toJson())
        return LoginService.parserJson(json)
    }
    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }

}