package com.dicoding.academy.themealsapp.core.data.remote.reponse

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @field:SerializedName("categories")
    val categories: List<CategoryResponse>,
)

data class CategoryResponse(
    @field:SerializedName("idCategory")
    val id: String,

    @field:SerializedName("strCategory")
    val title: String,

    @field:SerializedName("strCategoryThumb")
    val image: String,

    @field:SerializedName("strCategoryDescription")
    val description: String,

)