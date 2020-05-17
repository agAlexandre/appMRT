package com.example.mrtOficinas

import androidx.room.Room

object DatabaseManager {

    private var dbInstance: MRTDatabase

    init {
        val appContext = MRTApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
            appContext,
            MRTDatabase::class.java,
            "mrt.sqllite"
        ).build()
    }
    fun getServicoDAO():servicosDAO{
        return dbInstance.servicosDAO()
    }

    fun getLoginDAO():loginDAO{
        return dbInstance.loginDAO()
    }

}