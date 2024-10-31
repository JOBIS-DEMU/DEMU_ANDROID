package com.example.demu_android.data.sharedpreference

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceManager {
    private var sharedPreferences: SharedPreferences? = null

    fun getInstance(context: Context): SharedPreferences {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("demu", Context.MODE_PRIVATE)
        }
        return sharedPreferences!!
    }
}