package com.dicoding.academy.themealsapp.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign

@Composable
fun EmptyView() {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Tidak ada data, mohon coba kembali.",
            textAlign = TextAlign.Center
        )
    }
}