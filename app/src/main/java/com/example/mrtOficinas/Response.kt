package com.example.mrtOficinas

data class Response(val status: String, val msg: String) {
    fun isOk()="OK".equals(status,ignoreCase = true)
}