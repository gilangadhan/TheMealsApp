package com.dicoding.academy.themealsapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.dicoding.academy.themealsapp.core.data.IMealRepository
import com.dicoding.academy.themealsapp.core.domain.model.CategoryModel
import com.dicoding.academy.themealsapp.core.domain.model.MealModel

interface MealUseCase {
    fun getCategories(): LiveData<List<CategoryModel>>
    fun getMeals(category: String): LiveData<List<MealModel>>
    fun searchMeals(keyword: String): LiveData<List<MealModel>>
    fun getFavoriteCategories(): LiveData<List<CategoryModel>>
    fun addFavoriteCategory(category: CategoryModel)
    fun getFavoriteCategoryBy(id: String): LiveData<CategoryModel>
    fun deteleFavoriteCategory(id: String)
}


class MealInteractor(private val mealRepository: IMealRepository): MealUseCase {
    
    override fun getCategories() = mealRepository.getCategories()

    override fun getMeals(category: String) = mealRepository.getMeals(category)
    override fun searchMeals(keyword: String) = mealRepository.searchMeals(keyword)

    override fun getFavoriteCategories() = mealRepository.getFavoriteCategories()

    override fun addFavoriteCategory(category: CategoryModel) = mealRepository.addFavoriteCategory(category)

    override fun getFavoriteCategoryBy(id: String): LiveData<CategoryModel> = mealRepository.getFavoriteCategoryBy(id)
    override fun deteleFavoriteCategory(id: String) {
        mealRepository.deleteFavoriteCategory(id)
    }
}
