package com.example.test2.usecase

import com.example.test2.common.ApiState
import com.example.test2.model.auth.LoginRequest
import com.example.test2.repository.AuthRepositoryImpl

class LoginUseCase(
    private val authRepositoryImpl: AuthRepositoryImpl
) {
    suspend operator fun invoke(
        loginRequest: LoginRequest
    ) : ApiState {
        val error = if (loginRequest.password.isBlank() || loginRequest.username.isBlank()) "Invalid request" else null

        if (error != null){
            return ApiState(
                error
            )
        }

        return ApiState(
            result = authRepositoryImpl.login(loginRequest)
        )
    }
}