package com.dicoding.academy.themealsapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object DetailCategory : Screen("home/{rewardId}") {
        fun createRoute(categoryID: String) = "home/$categoryID"
    }
}