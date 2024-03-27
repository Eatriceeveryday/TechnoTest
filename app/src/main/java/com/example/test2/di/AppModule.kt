package com.example.test2.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.test2.data.local.UserPreferences
import com.example.test2.data.remote.ApiService
import com.example.test2.repository.AuthRepositoryImpl
import com.example.test2.repository.HomeRepositoryImpl
import com.example.test2.usecase.LoginUseCase
import com.example.test2.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePreferencesDatastore(@ApplicationContext contexts: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(produceFile = {
        contexts.preferencesDataStoreFile(Constant.AUTH_PREFERENCES)
    })

    @Provides
    @Singleton
    fun provideAuthPreferences(dataStore: DataStore<Preferences>) = UserPreferences(dataStore)

    @Provides
    @Singleton
    fun provideApiService(): ApiService{

        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        apiService: ApiService,
        userPreferences: UserPreferences
    ): AuthRepositoryImpl{
       return AuthRepositoryImpl(
           apiService, userPreferences
       )
    }

    @Provides
    @Singleton
    fun provideHomeRepository(
        apiService: ApiService,
        userPreferences: UserPreferences
    ): HomeRepositoryImpl{
        return HomeRepositoryImpl(
            apiService, userPreferences
        )
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(repositoryImpl: AuthRepositoryImpl):LoginUseCase{
        return LoginUseCase(repositoryImpl)
    }

}