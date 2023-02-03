package com.dicoding.academy.themealsapp.module.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.dicoding.academy.themealsapp.R
import com.dicoding.academy.themealsapp.core.domain.model.CategoryModel
import com.dicoding.academy.themealsapp.ui.theme.MyMovieTheme
import com.dicoding.academy.themealsapp.ui.theme.Shapes
import com.dicoding.academy.themealsapp.ui.theme.randomColor

@Composable
fun CategoryRow(
    categoryModel: CategoryModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
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
        Column(
            modifier = modifier
                .padding(start = 16.dp, top = 0.dp, bottom = 16.dp, end = 0.dp),
        ) {
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
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RewardItemPreview() {
    MyMovieTheme {
        CategoryRow(
            CategoryModel(
                id = "1",
                title = "Title",
                image = "https://www.themealdb.com/images/category/beef.png",
                description = "description"
            )
        )
    }
}