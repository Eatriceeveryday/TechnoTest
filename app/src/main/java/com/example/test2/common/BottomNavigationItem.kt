package com.example.test2.common

import android.graphics.drawable.VectorDrawable
import androidx.annotation.StringRes
import com.example.test2.R

sealed class BottomNavigationItem(val route: String, name: String,val icon: Int){
    object Dashboard: BottomNavigationItem("dashboard","Home", R.drawable.home1)
    object Menu: BottomNavigationItem("menu", "Menu", R.drawable.menu1)
}
