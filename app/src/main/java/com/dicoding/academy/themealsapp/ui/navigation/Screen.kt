package com.dicoding.academy.themealsapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Search : Screen("search")
    object Profile : Screen("profile")
    object DetailCategory : Screen("home/{categoryModel}")

}