package com.dicoding.academy.themealsapp.core.data.locale

import androidx.lifecycle.LiveData
import com.dicoding.academy.themealsapp.core.data.locale.entity.CategoryEntity
import com.dicoding.academy.themealsapp.core.data.locale.room.MealDao

class LocalDataSource private constructor(private val mealDao: MealDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(mealDao: MealDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(mealDao)
            }
    }

    fun getFavoriteCategories(): LiveData<List<CategoryEntity>> = mealDao.getCategories()

    fun getCategoryBy(id: String): LiveData<CategoryEntity> = mealDao.getCategoryBy(id)

    fun addCategory(category: CategoryEntity) = mealDao.addCategory(category)
}