package com.dicoding.academy.themealsapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealModel(
    val id: String,
    val title: String,
    val image: String
): Parcelable