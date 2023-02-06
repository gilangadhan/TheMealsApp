package com.dicoding.academy.themealsapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.dicoding.academy.themealsapp.core.data.IMealRepository
import com.dicoding.academy.themealsapp.core.domain.model.CategoryModel

interface MealUseCase {
    fun getCategories(): LiveData<List<CategoryModel>>
    fun getFavoriteCategories(): LiveData<List<CategoryModel>>
    fun addFavoriteCategory(category: CategoryModel)
    fun getFavoriteCategoryBy(id: String): LiveData<CategoryModel>
    fun deteleFavoriteCategory(id: String)
}


class MealInteractor(private val mealRepository: IMealRepository): MealUseCase {
    
    override fun getCategories() = mealRepository.getCategories()
    
    override fun getFavoriteCategories() = mealRepository.getFavoriteCategories()

    override fun addFavoriteCategory(category: CategoryModel) = mealRepository.addFavoriteCategory(category)

    override fun getFavoriteCategoryBy(id: String): LiveData<CategoryModel> = mealRepository.getFavoriteCategoryBy(id)
    override fun deteleFavoriteCategory(id: String) {
        mealRepository.deleteFavoriteCategory(id)
    }
}
