package com.example.test2.presentation.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.test2.common.BottomNavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScree(navHost: NavHostController,){
    Text(text = "This is home screen")

    val bottomNavigation = listOf(
        BottomNavigationItem.Dashboard,
        BottomNavigationItem.Menu
    )

    /*Scaffold(
        bottomBar = {
            Bot
        }
    ) {

    }*/
}