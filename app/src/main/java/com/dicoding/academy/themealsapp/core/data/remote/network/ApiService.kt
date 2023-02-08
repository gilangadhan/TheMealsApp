package com.dicoding.academy.themealsapp.core.data.remote.network

import com.dicoding.academy.themealsapp.core.data.remote.reponse.CategoriesResponse
import com.dicoding.academy.themealsapp.core.data.remote.reponse.MealsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("categories.php")
    fun getCategories(): Call<CategoriesResponse>

    @GET("filter.php")
    fun getMeals(@Query("c") by: String): Call<MealsResponse>

    @GET("search.php")
    fun searchMeal(@Query("s") by: String): Call<MealsResponse>
}