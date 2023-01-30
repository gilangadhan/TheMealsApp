package com.dicoding.academy.themealsapp.module.favorite

import androidx.lifecycle.ViewModel
import com.dicoding.academy.themealsapp.core.domain.usecase.MealUseCase

class FavoriteViewModel(
    private val mealUseCase: MealUseCase
) : ViewModel() {

    val categories = mealUseCase.getFavoriteCategories()

}