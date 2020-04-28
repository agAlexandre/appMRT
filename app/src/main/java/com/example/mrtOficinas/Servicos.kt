package com.example.mrtOficinas

import com.google.gson.GsonBuilder

class Servicos {
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