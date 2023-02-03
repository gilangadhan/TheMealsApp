package com.dicoding.academy.themealsapp.module.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.academy.themealsapp.core.domain.model.CategoryModel
import com.dicoding.academy.themealsapp.core.domain.usecase.MealUseCase
import com.dicoding.academy.themealsapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val mealUseCase: MealUseCase
) : ViewModel() {
    var categories: LiveData<List<CategoryModel>> = MutableLiveData<List<CategoryModel>>()

    fun getCategories() {
        categories = mealUseCase.getCategories()
    }
}