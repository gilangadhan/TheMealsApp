package com.dicoding.academy.themealsapp.module.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.dicoding.academy.themealsapp.core.di.Injection
import com.dicoding.academy.themealsapp.core.domain.model.MealModel
import com.dicoding.academy.themealsapp.module.detail.MealRow
import com.dicoding.academy.themealsapp.ui.common.ViewModelFactory
import com.dicoding.academy.themealsapp.ui.view.EmptyView

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideMealUseCase(LocalContext.current))
    ),
) {
    var keyword by rememberSaveable { mutableStateOf("") }
    var isShowed by rememberSaveable { mutableStateOf(false) }

    val meals = viewModel.searchMeals(keyword).observeAsState().value

    SearchContent(
        isShowed = isShowed,
        meals = meals,
    ) {
        keyword = it
        viewModel.searchMeals(it)
    }

    meals.let {
        if (it != null && it.isNotEmpty()) {
            isShowed = true
        }
    }
}


@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    meals: List<MealModel>?,
    isShowed: Boolean,
    searchButton: (String) -> Unit,
) {
    var keyword by rememberSaveable { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Row {
            TextField(
                value = keyword,
                onValueChange = {
                    keyword = it
                },
                label = { Text("Find your favorite food.") },
                modifier = modifier.weight(1f)
            )
            Button(onClick = {
                if (keyword.isNotEmpty()) {
                    searchButton(keyword)
                }
            }) {
                Icon(
                    tint = Color.White,
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                )
            }
        }
        if (isShowed && meals != null) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(160.dp),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = modifier
            ) {
                items(meals) { data ->
                    MealRow(
                        image = data.image,
                        title = data.title
                    )
                }
            }
        } else {
            EmptyView()
        }
    }
}