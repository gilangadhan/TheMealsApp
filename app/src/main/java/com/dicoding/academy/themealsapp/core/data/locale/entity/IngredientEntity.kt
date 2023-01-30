package com.dicoding.academy.themealsapp.core.data.locale.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient")
data class IngredientEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "idMeal")
    var idMeal: String,
)