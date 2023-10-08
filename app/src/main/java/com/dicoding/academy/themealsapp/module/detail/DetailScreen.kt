package com.dicoding.academy.themealsapp.module.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.dicoding.academy.themealsapp.core.di.Injection
import com.dicoding.academy.themealsapp.core.domain.model.CategoryModel
import com.dicoding.academy.themealsapp.ui.common.ViewModelFactory
import com.dicoding.academy.themealsapp.ui.theme.Shapes
import com.dicoding.academy.themealsapp.ui.view.LoadingView


@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideMealUseCase(LocalContext.current))
    ),
    categoryModel: CategoryModel,
) {
    var isFavorite by rememberSaveable { mutableStateOf(false) }
    val category = viewModel.getCategory(categoryModel.id).observeAsState().value
    val meals =  viewModel.getMeals(categoryModel.title).observeAsState().value

    isFavorite = (category != null && category.id.isNotEmpty())

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Box(contentAlignment = Alignment.TopEnd) {
            IconToggleButton(
                checked = isFavorite,
                onCheckedChange = {
                    if (isFavorite) {
                        viewModel.deleteCategory(categoryModel.id)
                    } else {
                        viewModel.addCategory(categoryModel)
                    }
                    isFavorite = !isFavorite
                },
            ) {
                Icon(
                    tint = Color.Red,
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = null
                )
            }
            SubcomposeAsyncImage(
                model = categoryModel.image,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                loading = {
                    CircularProgressIndicator()
                },
                modifier = Modifier
                    .size(200.dp)
                    .clip(Shapes.medium)
            )
        }
        Text(
            text = categoryModel.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = categoryModel.description,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1
        )
        Spacer(modifier = Modifier.width(16.dp))

        meals.let {
            if (it != null && it.isNotEmpty()) {
                Text(
                    text = "Several menus of ${categoryModel.title}:"
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(160.dp),
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = modifier
                    ) {
                        items(it) { data ->
                            MealRow(
                                image = data.image,
                                title = data.title
                            )
                        }
                    }
                }
            } else if (it != null) {
                LoadingView()
            } else {
                viewModel.getMeals(categoryModel.title)
            }
        }
    }
}