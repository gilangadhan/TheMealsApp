package com.dicoding.academy.themealsapp.core.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.map
import com.dicoding.academy.themealsapp.core.data.locale.LocalDataSource
import com.dicoding.academy.themealsapp.core.data.remote.RemoteDataSource
import com.dicoding.academy.themealsapp.core.domain.model.CategoryModel
import com.dicoding.academy.themealsapp.core.domain.model.MealModel
import com.dicoding.academy.themealsapp.core.utils.AppExecutors
import com.dicoding.academy.themealsapp.core.utils.DataMapper
import kotlin.math.log

interface IMealRepository {
    fun getCategories(): LiveData<List<CategoryModel>>
    fun getMeals(category: String): LiveData<List<MealModel>>
    fun searchMeals(keyword: String): LiveData<List<MealModel>>
    fun getFavoriteCategories(): LiveData<List<CategoryModel>>
    fun addFavoriteCategory(category: CategoryModel)
    fun getFavoriteCategoryBy(id: String): LiveData<CategoryModel>
    fun deleteFavoriteCategory(id: String)
}

class MealRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMealRepository {

    companion object {
        @Volatile
        private var instance: MealRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MealRepository =
            instance ?: synchronized(this) {
                instance ?: MealRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getCategories(): LiveData<List<CategoryModel>> {
        return remoteDataSource.getCategories().map {
            DataMapper.mapCategoryResponsesToDomain(it)
        }
    }

    override fun getMeals(category: String): LiveData<List<MealModel>> {
        return remoteDataSource.getMeals(category).map {
            DataMapper.mapMealResponsesToDomain(it)
        }
    }

    override fun searchMeals(keyword: String): LiveData<List<MealModel>> {
        return remoteDataSource.searchMeals(keyword).map {
            DataMapper.mapMealResponsesToDomain(it)
        }
    }
    override fun getFavoriteCategories(): LiveData<List<CategoryModel>> {
        return localDataSource.getFavoriteCategories().map {
            DataMapper.mapCategoryEntitiesToDomain(it)
        }
    }

    override fun addFavoriteCategory(category: CategoryModel) {
        val categoryEntity = DataMapper.mapCategoryDomainToEntity(category)
        appExecutors.diskIO().execute { localDataSource.addCategory(categoryEntity) }
    }

    override fun getFavoriteCategoryBy(id: String): LiveData<CategoryModel>  {
        val result = localDataSource.getCategoryBy(id)

        val mediatorLiveData = MediatorLiveData<CategoryModel>()

        mediatorLiveData.addSource(result) {
            if (it != null) {
                mediatorLiveData.value = DataMapper.mapCategoryEntityToDomain(it)
            }
        }

        return mediatorLiveData
    }

    override fun deleteFavoriteCategory(id: String) {
        appExecutors.diskIO().execute { localDataSource.deleteCategory(id) }
    }
}