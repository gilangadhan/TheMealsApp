package com.dicoding.academy.themealsapp.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dicoding.academy.themealsapp.core.data.locale.LocalDataSource
import com.dicoding.academy.themealsapp.core.data.remote.RemoteDataSource
import com.dicoding.academy.themealsapp.core.domain.model.CategoryModel
import com.dicoding.academy.themealsapp.core.utils.AppExecutors
import com.dicoding.academy.themealsapp.core.utils.DataMapper

interface IMealRepository {
    fun getCategories(): LiveData<List<CategoryModel>>
    fun getFavoriteCategories(): LiveData<List<CategoryModel>>
    fun addFavoriteCategory(category: CategoryModel)
    fun getFavoriteCategoryBy(id: String): LiveData<CategoryModel>
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
        return Transformations.map(remoteDataSource.getCategories()) {
            DataMapper.mapResponsesToDomain(it)
        }
    }

    override fun getFavoriteCategories(): LiveData<List<CategoryModel>> {
        return Transformations.map(localDataSource.getFavoriteCategories()) {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun addFavoriteCategory(category: CategoryModel) {
        val tourismEntity = DataMapper.mapDomainToEntity(category)
        appExecutors.diskIO().execute { localDataSource.addCategory(tourismEntity) }
    }

    override fun getFavoriteCategoryBy(id: String): LiveData<CategoryModel>  {
        return Transformations.map(localDataSource.getCategoryBy(id)) {
            DataMapper.mapEntityToDomain(it)
        }
    }
}