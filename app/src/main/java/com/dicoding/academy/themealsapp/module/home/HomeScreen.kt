package com.dicoding.academy.themealsapp.module.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.academy.themealsapp.core.di.Injection
import com.dicoding.academy.themealsapp.core.domain.model.CategoryModel
import com.dicoding.academy.themealsapp.ui.common.ViewModelFactory
import com.dicoding.academy.themealsapp.ui.view.EmptyView
import com.dicoding.academy.themealsapp.ui.view.LoadingView

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideMealUseCase(LocalContext.current))
    ),
    navigateToDetail: (CategoryModel) -> Unit,
) {
    val categories  = viewModel.getCategories().observeAsState().value

    categories.let {
        if (it != null && it.isNotEmpty()) {
            HomeContent(
                categories = it,
                modifier = modifier,
                navigateToDetail = navigateToDetail,
            )
        } else if (it != null){
            LoadingView()
        } else {
            EmptyView()
            viewModel.getCategories()
        }
    }
}

@Composable
fun HomeContent(
    categories: List<CategoryModel>,
    modifier: Modifier = Modifier,
    navigateToDetail: (CategoryModel) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(categories) { data ->
            CategoryRow(
                data,
                modifier = Modifier.clickable {
                    navigateToDetail(data)
                }
            )
        }

    }
}