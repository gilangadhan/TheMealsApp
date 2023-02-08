package com.dicoding.academy.themealsapp.core.data.remote.reponse

import com.google.gson.annotations.SerializedName

data class MealsResponse (

    @SerializedName("meals")
    var meals: ArrayList<MealResponse> = arrayListOf()
)

data class MealResponse (
    @SerializedName("strMeal")
    var title: String = "",
    @SerializedName("strMealThumb")
    var image: String = "",
    @SerializedName("idMeal")
    var id: String = ""
)