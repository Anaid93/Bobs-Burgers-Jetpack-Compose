package com.example.movies.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movies.R
import com.example.movies.ui.theme.screens.HomeScreen
import com.example.movies.ui.theme.screens.MoviesViewModel


@Composable
fun MoviesApp(modifier: Modifier = Modifier){
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {
            val moviesViewModel: MoviesViewModel =
                viewModel(factory = MoviesViewModel.Factory)
            HomeScreen(
                moviesUiState = moviesViewModel.moviesUiState,
                retryAction = moviesViewModel::getMovies
            )
        }
    }
}

