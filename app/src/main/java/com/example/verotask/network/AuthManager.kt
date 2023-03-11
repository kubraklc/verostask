package com.example.verotask.network

import android.content.Context
import android.content.SharedPreferences
import com.example.verotask.R

class AuthManager (context: Context){

    // KOD KALABALIĞINI ENGELLEMEK İÇN AUTHMANAGER CLASSI OLUŞTURDUK
    // BUNU YAPMAZSAK BU KODLARI HER YERE ELLE YAPIŞTIRMAMIZ GEREKECEKTİ
    private var prefs : SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object{
        const val Token = "access_token"
    }

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(Token, token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
         return prefs.getString(Token, null)
    }

}