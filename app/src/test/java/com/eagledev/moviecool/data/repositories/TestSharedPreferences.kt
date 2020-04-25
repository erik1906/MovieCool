package com.eagledev.moviecool.data.repositories

import com.eagledev.moviecool.data.AppSharedPreferences

class TestSharedPreferences : AppSharedPreferences {

    private val  mapPreferences = mutableMapOf(
        "accessToken" to "accessToken",
        "accountId" to "accountId"
    )
    override fun isLogin(login: Boolean) {

    }

    override fun isLogin(): Boolean {
        return true}

    override fun putSharedPreference(key: String, data: String) {
        mapPreferences[key] = data
    }

    override fun getSharedPreferences(key: String): String {
        return  mapPreferences[key] ?: ""
    }

}