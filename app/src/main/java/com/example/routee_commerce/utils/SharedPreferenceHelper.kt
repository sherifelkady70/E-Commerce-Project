package com.example.routee_commerce.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import java.util.prefs.Preferences
import javax.inject.Inject


class SharedPreferenceHelper @Inject constructor(
    private val sharedPreferences: SharedPreferences) {

    fun saveToken(token : String){
        with(sharedPreferences.edit()){
            putString("token",token)
            apply()
        }
    }

    fun getToken() : String? {
        return sharedPreferences.getString("token",null)
    }
}