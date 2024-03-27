package com.example.test2.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.test2.util.Constant
import kotlinx.coroutines.flow.first

class UserPreferences(private val dataStore: DataStore<Preferences>) {

    suspend fun saveAccessToken(token:String){
        dataStore.edit { pref ->
            pref[Constant.ACCESS_TOKEN] = token
        }
    }

    suspend fun saveTokenType(tokenType:String){
        dataStore.edit { pref ->
            pref[Constant.TOKEN_TYPE] = tokenType
        }
    }

    suspend fun getAccessToken():String{
        val preferences = dataStore.data.first()
        return preferences[Constant.ACCESS_TOKEN] ?: ""
    }

    suspend fun getTokenType():String {
        val preferences = dataStore.data.first()
        return preferences[Constant.TOKEN_TYPE] ?: ""
    }
}