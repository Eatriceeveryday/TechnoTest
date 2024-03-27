package com.example.test2.repository

import android.util.Log
import com.example.test2.common.Resource
import com.example.test2.data.local.UserPreferences
import com.example.test2.data.remote.ApiService
import com.example.test2.model.auth.LoginRequest
import com.example.test2.usecase.LoginUseCase
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
) {
    suspend fun login(loginRequest: LoginRequest): Resource<Unit>{
        return try {
            val response = apiService.login(
                grantType = loginRequest.grantType,
                clientId = loginRequest.clientId,
                clientSecret = loginRequest.clientSecret,
                username = loginRequest.username,
                password = loginRequest.password)
            print(response)
            userPreferences.saveAccessToken(response.accessToken)
            userPreferences.saveTokenType(response.tokenType)
            Resource.Success(Unit)
        }catch (e: IOException){
            Log.d("Error" , "IO")
            Resource.Error(e.message.toString())
        }catch (e: HttpException){
            println(e)
            Resource.Error(e.message())
        } catch (e: Exception) {
            Log.e("Error", "Unexpected exception during login:", e)
            Resource.Error("An error occurred. Please try again later.")
        }
    }
}