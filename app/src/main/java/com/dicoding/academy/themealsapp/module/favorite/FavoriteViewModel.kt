package com.dicoding.academy.themealsapp.module.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.academy.themealsapp.core.domain.model.CategoryModel
import com.dicoding.academy.themealsapp.core.domain.usecase.MealUseCase

class FavoriteViewModel(
    private val mealUseCase: MealUseCase
) : ViewModel() {
    var categories: LiveData<List<CategoryModel>> = MutableLiveData()
    fun getCategories() {
        categories = mealUseCase.getFavoriteCategories()
    }
}