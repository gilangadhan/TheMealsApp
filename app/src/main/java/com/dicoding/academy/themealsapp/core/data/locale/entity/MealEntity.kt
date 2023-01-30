package com.dicoding.academy.themealsapp.core.data.locale.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal")
data class MealEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "area")
    var area: String,

    @ColumnInfo(name = "instructions")
    var instructions: String,

    @ColumnInfo(name = "tag")
    var tag: String,

    @ColumnInfo(name = "youtube")
    var youtube: String,

    @ColumnInfo(name = "source")
    var source: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false,

    @ColumnInfo(name = "ingredients")
    var ingredients: List<IngredientEntity>,

)

