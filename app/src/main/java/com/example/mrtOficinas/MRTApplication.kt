package com.example.mrtOficinas

import android.app.Application
import java.lang.IllegalStateException

class MRTApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: MRTApplication?= null

        fun getInstance():MRTApplication{
            if (appInstance==null){
                throw IllegalStateException("Configurar Application no Manifest")
            }
            return appInstance!!
        }
    }
    override fun onTerminate(){
        super.onTerminate()
    }
}
