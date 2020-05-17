package com.example.mrtOficinas
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities= arrayOf(Servicos::class , Login::class),version = 2)
abstract class MRTDatabase: RoomDatabase() {
    abstract fun servicosDAO(): servicosDAO

    abstract fun loginDAO(): loginDAO

}