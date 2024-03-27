package com.example.test2.common

sealed class HomeEvent {
    data class HistoryPage(val page : String): HomeEvent()
    data class RecommendationPage(val page : String): HomeEvent()
    data class NoHistoryPage(val page : String): HomeEvent()
    data class ToastEvent(val msg: String): HomeEvent()
}