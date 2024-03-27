package com.example.test2.data.remote

import com.example.test2.model.auth.LoginRequest
import com.example.test2.model.auth.LoginResponse
import com.example.test2.model.home.HomeResponse
import com.example.test2.model.menu.MenuResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun login(
        @Field("grant_type")grantType: String,
        @Field("client_secret")clientSecret: String,
        @Field("client_id")clientId: String,
        @Field("username")username: String,
        @Field("password")password:String
    ):LoginResponse

    @Headers("Content-Type: application/json")
    @GET("api/home")
    suspend fun home(
        @Header("Authorization") token: String
    ): HomeResponse

    @Headers("Content-Type: application/json")
    @POST("api/home")
    suspend fun getMenu(
        @Header("Authorization") token: String,
        @Body show_all: String = "1"
    ): MenuResponse
}