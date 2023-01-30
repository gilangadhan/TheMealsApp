package com.dicoding.academy.themealsapp.module.detail

import androidx.lifecycle.ViewModel
import com.dicoding.academy.themealsapp.core.domain.model.CategoryModel
import com.dicoding.academy.themealsapp.core.domain.usecase.MealUseCase

class DetailViewModel(
    private val mealUseCase: MealUseCase
) : ViewModel() {

    fun getCategory(id: String) = mealUseCase.getFavoriteCategoryBy(id)

    fun addCategory(categoryModel: CategoryModel) {
        mealUseCase.addFavoriteCategory(categoryModel)
    }
}