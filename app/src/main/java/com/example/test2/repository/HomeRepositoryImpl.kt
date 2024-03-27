package com.example.test2.repository

import com.example.test2.common.Resource
import com.example.test2.data.local.UserPreferences
import com.example.test2.data.remote.ApiService
import com.example.test2.model.home.HomeResult
import com.example.test2.model.menu.MenuResult
import retrofit2.HttpException
import java.io.IOException

class HomeRepositoryImpl(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
) {
    suspend fun home(): Resource<HomeResult>{
        return try {
            val token = "${userPreferences.getTokenType()} ${userPreferences.getAccessToken()}"
            val response = apiService.home(token)
            Resource.Success(response.result)
        }catch (e: IOException){
            Resource.Error(message = e.message.toString())
        }catch (e: HttpException){
            print(e)
            Resource.Error(message = e.message.toString())
        }
    }

    suspend fun getMenu(): Resource<MenuResult>{
        return try {
            val token = "${userPreferences.getTokenType()} ${userPreferences.getAccessToken()}"
            val response = apiService.getMenu(token)
            Resource.Success(response.result)
        }catch (e: IOException){
            Resource.Error(message = e.message.toString())
        }catch (e: HttpException){
            print(e)
            Resource.Error(message = e.message.toString())
        }
    }

}