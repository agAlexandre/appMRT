package com.example.mrtOficinas

import android.content.SharedPreferences

object Prefs {
    val PREF_ID="MRT"

    private fun prefs(): SharedPreferences{
        val context = MRTApplication.getInstance().applicationContext
        return context.getSharedPreferences(PREF_ID,0)
    }

    fun setBoolean(flag:String, valor: Boolean) = prefs().edit().putBoolean(flag, valor).apply()

    fun getBoolean(flag: String) = prefs().getBoolean(flag, false)

    fun setString(flag: String, valor: String)= prefs().edit().putString(flag,valor).apply()

    fun getString(flag: String)= prefs().getString(flag,"")

    fun setFloat(flag:String, valor: Float)= prefs().edit().putFloat(flag,valor).apply()

    fun getFloat(flag:String)= prefs().getFloat(flag,0F)

    fun setInt(flag:String, valor: Int)= prefs().edit().putInt(flag,valor).apply()

    fun getInt(flag:String)= prefs().getInt(flag,0)
}