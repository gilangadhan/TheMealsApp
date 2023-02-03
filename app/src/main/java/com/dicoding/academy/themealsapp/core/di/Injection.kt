package com.dicoding.academy.themealsapp.core.di

import android.content.Context
import com.dicoding.academy.themealsapp.core.data.IMealRepository
import com.dicoding.academy.themealsapp.core.data.MealRepository
import com.dicoding.academy.themealsapp.core.data.locale.LocalDataSource
import com.dicoding.academy.themealsapp.core.data.locale.room.MealDatabase
import com.dicoding.academy.themealsapp.core.data.remote.RemoteDataSource
import com.dicoding.academy.themealsapp.core.data.remote.network.ApiConfig
import com.dicoding.academy.themealsapp.core.domain.usecase.MealInteractor
import com.dicoding.academy.themealsapp.core.domain.usecase.MealUseCase
import com.dicoding.academy.themealsapp.core.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): IMealRepository {
        val database = MealDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.mealDao())
        val appExecutors = AppExecutors()
        return MealRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideMealUseCase(context: Context): MealUseCase {
        val repository = provideRepository(context)
        return MealInteractor(repository)
    }
}