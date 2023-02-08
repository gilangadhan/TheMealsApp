package com.dicoding.academy.themealsapp.core.data.remote.reponse

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @field:SerializedName("categories")
    var categories: List<CategoryResponse> = arrayListOf(),
)

data class CategoryResponse(
    @field:SerializedName("idCategory")
    var id: String = "",

    @field:SerializedName("strCategory")
    var title: String = "",

    @field:SerializedName("strCategoryThumb")
    var image: String = "",

    @field:SerializedName("strCategoryDescription")
    var description: String = "",

)