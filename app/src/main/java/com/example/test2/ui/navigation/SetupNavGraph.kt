package com.example.test2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.test2.presentation.auth.LoginScreen
import com.example.test2.presentation.home.HomeScree

@Composable
fun SetupNavGraph(
    navHostController: NavHostController
){
    NavHost(navController = navHostController, startDestination = Screen.Login.route ){
        composable(route = Screen.Login.route){
            LoginScreen(navHost = navHostController)
        }

        composable(route= Screen.home.route){
            HomeScree(navHost = navHostController)
        }
    }
}