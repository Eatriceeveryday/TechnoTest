package com.example.test2.presentation.home

import android.graphics.drawable.Icon
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.test2.common.BottomNavigationItem

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    items: List<BottomNavigationItem>
){
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        /*items.forEach { screen ->
            BottomNavigationItem(
                label = { Text(text = screen.name)},
                selected = currentDestination?.hierarchy?.any() { it.route == screen.route } == true,,
                onClick = { *//*TODO*//* },
                icon = { *//*TODO*//* })
        }*/
    }
}