package com.example.mrtOficinas

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface servicosDAO {
    @Query("SELECT * FROM servicos WHERE id=:id")
    fun getById(id:Long): Servicos?

    @Query("SELECT * FROM servicos")
    fun findAll():List<Servicos>

    @Insert
    fun insert(servicos: Servicos)

    @Delete
    fun delete(servicos: Servicos)

}
