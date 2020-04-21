package com.eagledev.moviecool.utils

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton


class ResourceProvider @Inject constructor(private val context: Context){

    fun getString(resId: Int): String{
        return context.getString(resId)
    }

}