package com.dicoding.academy.themealsapp.module.home

import androidx.lifecycle.ViewModel
import com.dicoding.academy.themealsapp.core.domain.usecase.MealUseCase

class HomeViewModel(
    private val mealUseCase: MealUseCase
) : ViewModel() {
    val categories = mealUseCase.getCategories()
}