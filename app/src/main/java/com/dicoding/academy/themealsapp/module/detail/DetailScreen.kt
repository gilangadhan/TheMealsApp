package com.dicoding.academy.themealsapp.module.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.dicoding.academy.themealsapp.core.di.Injection
import com.dicoding.academy.themealsapp.core.domain.model.CategoryModel
import com.dicoding.academy.themealsapp.ui.common.ViewModelFactory
import com.dicoding.academy.themealsapp.ui.theme.MyMovieTheme
import com.dicoding.academy.themealsapp.ui.theme.Shapes

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

    isFavorite = (category != null && category.id.isNotEmpty())

    DetailContent(
        categoryModel = categoryModel,
        modifier = modifier,
        isFavorite = isFavorite,
    ) {
        if (isFavorite) {
            viewModel.deleteCategory(categoryModel.id)
        } else {
            viewModel.addCategory(categoryModel)
        }
        isFavorite != isFavorite
    }
}

@Composable
fun DetailContent(
    categoryModel: CategoryModel,
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    favoriteButton: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
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
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1
        )
        Button(onClick = favoriteButton) {
            Text(
                text = if (isFavorite) "Favorite" else "Tidak Favorite",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle1
            )
        }

    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    MyMovieTheme {
        DetailContent(
            categoryModel = CategoryModel(
                id = "1",
                title = "Title",
                image = "https://www.themealdb.com/images/category/beef.png",
                description = "description"
            ),
            isFavorite = false,
        ) {

        }
    }
}