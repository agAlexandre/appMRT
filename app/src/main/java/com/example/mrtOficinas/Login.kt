package com.example.mrtOficinas

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName="login")
class Login: Serializable {
    @PrimaryKey
    var usuario: String =""
    var senha: String = ""

    override fun toString(): String {
        return "Login($usuario,$senha)"
    }
    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}