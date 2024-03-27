package com.example.test2.presentation.auth

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test2.common.ModelState
import com.example.test2.common.Resource
import com.example.test2.common.TextFieldState
import com.example.test2.common.UiEvents
import com.example.test2.data.local.UserPreferences
import com.example.test2.model.auth.LoginRequest
import com.example.test2.repository.AuthRepositoryImpl
import com.example.test2.ui.navigation.Screen
import com.example.test2.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

    private val loginUseCase: LoginUseCase
):ViewModel() {

    private var _eventFlow = MutableSharedFlow<UiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var _email = mutableStateOf(TextFieldState())
    val email: MutableState<TextFieldState> = _email

    fun setEmail(value: String){
        _email.value = email.value.copy(text = value)
    }

    private var _password = mutableStateOf(TextFieldState())
    val password: MutableState<TextFieldState> = _password

    fun setPassword(value: String){
        _password.value = password.value.copy(text = value)
    }

    fun login(){
        viewModelScope.launch {
            _eventFlow.emit(
                UiEvents.LoadingEvent(true)
            )

            val loginRequest = LoginRequest(
                grantType = "password",
                clientSecret = "0a40f69db4e5fd2f4ac65a090f31b823",
                clientId = "e78869f77986684a ",
                username = email.value.text,
                password = password.value.text
            )

            val result = loginUseCase(loginRequest)

            _eventFlow.emit(
                UiEvents.LoadingEvent(false)
            )

            when(result.result){
                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvents.ErrorEvent(
                            "Login Gagal"
                        )
                    )
                }

                is Resource.Success -> {
                    _eventFlow.emit(
                        UiEvents.NavigateEvent(
                            Screen.home.route
                        )
                    )
                }
                else -> {}
            }

            Log.d("Check : " , result.result.toString())

            if (result.error != ""){
                _eventFlow.emit(
                    UiEvents.ErrorEvent(
                        result.error.toString()
                    )
                )
            }
        }
    }
}