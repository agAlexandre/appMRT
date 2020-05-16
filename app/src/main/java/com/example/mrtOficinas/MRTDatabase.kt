package com.example.mrtOficinas
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities= arrayOf(Servicos::class),version = 1)
abstract class MRTDatabase: RoomDatabase() {
    abstract fun servicosDAO(): servicosDAO

}