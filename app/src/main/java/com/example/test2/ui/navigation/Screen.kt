package com.example.test2.ui.navigation

sealed class Screen(val route: String) {
    object Login: Screen(route = "login")
    object home: Screen(route = "home")
}