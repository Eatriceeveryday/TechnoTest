package com.example.test2.model.auth

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("token_type")
    val tokenType: String,

    @field:SerializedName("expires_in")
    val expiresIn: String,

    @field:SerializedName("access_token")
    val accessToken: String,

    @field:SerializedName("refresh_token")
    val refreshToken: String
)
