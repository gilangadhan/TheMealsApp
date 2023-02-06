package com.dicoding.academy.themealsapp.ui.common

import com.dicoding.academy.themealsapp.core.domain.model.CategoryModel
import com.squareup.moshi.Moshi
import org.json.JSONObject

fun categoryModelToJSON(categoryModel: CategoryModel): String {
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter(CategoryModel::class.java).lenient()
    return jsonAdapter.toJson(categoryModel) as String
}

fun categoryJSONToModel(categoryJSON: String): CategoryModel {
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter(CategoryModel::class.java).lenient()
    return jsonAdapter.fromJson(categoryJSON) as CategoryModel
}