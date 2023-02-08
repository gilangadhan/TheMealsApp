package com.dicoding.academy.themealsapp.core.data.locale.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.academy.themealsapp.core.data.locale.entity.CategoryEntity

@Dao
interface MealDao {
    @Query("SELECT * FROM category ORDER BY title ASC")
    fun getCategories(): LiveData<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCategory(category: CategoryEntity)

    @Query("SELECT * FROM category WHERE id = :id ORDER BY title ASC")
    fun getCategoryBy(id: String): LiveData<CategoryEntity>

    @Query("DELETE FROM category WHERE id = :id")
    fun deleteCategory(id: String)
}