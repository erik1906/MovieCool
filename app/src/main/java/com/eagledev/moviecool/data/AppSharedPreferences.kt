package com.eagledev.moviecool.data

import android.content.SharedPreferences

class AppSharedPreferences constructor(private val sharedPreferences: SharedPreferences) {

    fun isLogin(login: Boolean){
        sharedPreferences.edit().putBoolean("login", login).apply()
    }

    fun isLogin() =
        sharedPreferences.getBoolean("login", false)


    fun putSharedPreference(key: String, data: String){
        sharedPreferences.edit().putString(key, data).apply()
    }

    fun getSharedPreferences(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }

}