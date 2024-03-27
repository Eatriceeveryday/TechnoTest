package com.example.test2.model.auth

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @field:SerializedName("grant_type")
    val grantType: String,

    @field:SerializedName("client_secret")
    val clientSecret: String,

    @field:SerializedName("client_id")
    val clientId: String,

    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("password")
    val password: String
)
