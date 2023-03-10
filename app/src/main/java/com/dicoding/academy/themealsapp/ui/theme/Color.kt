package com.dicoding.academy.themealsapp.ui.theme

import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

fun randomColor(): Color = Color(
    red = (0..266).random(),
    green = (0..266).random(),
    blue = (0..266).random()
)