package com.dicoding.academy.themealsapp.core.data.remote.network

import com.dicoding.academy.themealsapp.core.data.remote.reponse.CategoriesResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("categories.php")
    fun getCategories(): Call<CategoriesResponse>

}