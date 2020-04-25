package com.eagledev.moviecool.data

import android.content.SharedPreferences

interface AppSharedPreferences{
    fun isLogin(login: Boolean)
    fun isLogin(): Boolean
    fun putSharedPreference(key: String, data: String)
    fun getSharedPreferences(key: String): String
}

class DefaultAppSharedPreferences constructor(private val sharedPreferences: SharedPreferences): AppSharedPreferences {

    override fun isLogin(login: Boolean){
        sharedPreferences.edit().putBoolean("login", login).apply()
    }

    override fun isLogin() =
        sharedPreferences.getBoolean("login", false)


    override fun putSharedPreference(key: String, data: String){
        sharedPreferences.edit().putString(key, data).apply()
    }

    override fun getSharedPreferences(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }

}