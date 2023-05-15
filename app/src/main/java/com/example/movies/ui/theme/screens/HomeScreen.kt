package com.example.movies.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.RowScopeInstance.weight

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size
import com.example.movies.model.Movies
import com.example.movies.ui.theme.MoviesTheme
import com.example.movies.R

@Composable
fun HomeScreen(
    moviesUiState: MoviesUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
){
    when (moviesUiState) {
        is MoviesUiState.Loading -> LoadingScreen(modifier)
        is MoviesUiState.Success -> MoviesListScreen(moviesUiState.movies, modifier)
        else -> ErrorScreen(retryAction, modifier)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
    }
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.loading_failed))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun MovieCard(movies: Movies, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth(),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(movies.image)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                modifier = modifier
                    .fillMaxHeight()
                    .weight(0.5f)
            )

            Column(
            modifier = modifier
                .padding(start = 16.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                .fillMaxHeight()
                .weight(1f))
            {
                Text(
                    text = movies.name,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                TextName(textName = stringResource(R.string.occupation))
                movies.occupation?.let {
                    TextDesign(text = it)
                }
                TextName(textName = stringResource(R.string.first_episode))
                movies.firstEpisode?.let {
                    TextDesign(text = it)
                }
                TextName(textName = stringResource(R.string.voiced_by))
                movies.voicedBy?.let {
                    TextDesign(text = it)
                }
            }
        }
    }
}

@Composable
private fun MoviesListScreen(movies: List<Movies>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(
            items = movies,
            key = { movie ->
                movie.id
            }
        ) { movie ->
            MovieCard(movies = movie)
        }
    }
}

@Composable
fun TextDesign(text: String){
    Text(
        text = text,
        style = MaterialTheme.typography.body1
    )
}

@Composable
fun TextName(textName: String){
    Text(
        text = textName,
        color = Color.Gray,
        style = MaterialTheme.typography.body1,
    )
}