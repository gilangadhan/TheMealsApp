package com.dicoding.academy.themealsapp.ui.navigation

import com.dicoding.academy.themealsapp.core.domain.model.CategoryModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object DetailCategory : Screen("home/{categoryModel}") {
        fun createRoute(categoryModel: CategoryModel) = "home/$categoryModel"
    }
}