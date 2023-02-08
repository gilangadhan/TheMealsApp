package com.dicoding.academy.themealsapp.module.search

import androidx.lifecycle.ViewModel
import com.dicoding.academy.themealsapp.core.domain.usecase.MealUseCase

class SearchViewModel(
    private val mealUseCase: MealUseCase
) : ViewModel() {
    fun searchMeals(keyword: String) = mealUseCase.searchMeals(keyword)
}