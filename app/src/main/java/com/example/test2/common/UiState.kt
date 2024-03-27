package com.example.test2.common

sealed class UiEvents {
    data class ErrorEvent(val message : String) : UiEvents()
    data class NavigateEvent(val route: String) : UiEvents()
    data class LoadingEvent(val isLoading : Boolean ): UiEvents()
}