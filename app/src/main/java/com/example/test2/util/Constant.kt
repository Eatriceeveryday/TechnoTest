package com.example.test2.util

import androidx.datastore.preferences.core.stringPreferencesKey

object Constant {
    const val BASE_URL = "https://soal.staging.id/"
    const val AUTH_PREFERENCES = "auth_pref"
    val ACCESS_TOKEN = stringPreferencesKey("access_token")
    val TOKEN_TYPE = stringPreferencesKey("token_type")

}