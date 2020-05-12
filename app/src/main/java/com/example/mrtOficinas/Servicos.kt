package com.example.mrtOficinas

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName= "servicos")
class Servicos: Serializable {
    @PrimaryKey
    var id: Long =0
    var nome: String =""
    var descricao: String = ""
    var foto: String =""
    var valor: String = ""

    override fun toString(): String {
        return "Servicos(nome='$nome')"
    }
    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}