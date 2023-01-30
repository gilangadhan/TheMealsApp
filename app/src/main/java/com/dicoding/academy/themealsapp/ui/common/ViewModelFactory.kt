package com.dicoding.academy.themealsapp.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.academy.themealsapp.core.di.Injection
import com.dicoding.academy.themealsapp.core.domain.usecase.MealUseCase
import com.dicoding.academy.themealsapp.module.detail.DetailViewModel
import com.dicoding.academy.themealsapp.module.favorite.FavoriteViewModel
import com.dicoding.academy.themealsapp.module.home.HomeViewModel

class ViewModelFactory(private val mealUseCase: MealUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideMealUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(mealUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(mealUseCase) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mealUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}