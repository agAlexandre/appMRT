package com.example.mrtOficinas

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface loginDAO {

    @Query("SELECT * FROM login")
    fun findAllLogin():List<Login>

    @Insert
    fun insert(login: Login)

}