package com.radmanhayati.cafebazar.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.radmanhayati.cafebazar.domain.Movie
import com.radmanhayati.cafebazar.ui.component.ErrorComponent
import com.radmanhayati.cafebazar.ui.component.LoadingComponent
import com.radmanhayati.cafebazar.ui.component.RefreshErrorComponent
import com.radmanhayati.cafebazar.ui.component.RefreshLoadingComponent

@Composable
fun MovieScreen(
    movies: LazyPagingItems<Movie>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp)
            .background(color = Color.Black)
    ) {
        if (movies.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                color = Color.Green,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            val loadState = movies.loadState.mediator
            var isPaginatingError = false
            var error: Throwable? = null

            if (loadState?.refresh is LoadState.Error || loadState?.append is LoadState.Error) {
                isPaginatingError =
                    (loadState.append is LoadState.Error) || movies.itemCount > 1
                error = if (loadState.append is LoadState.Error)
                    (loadState.append as LoadState.Error).error
                else
                    (loadState.refresh as LoadState.Error).error

                if (!isPaginatingError) {
                    RefreshErrorComponent(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center),
                        error = error,
                        onRefreshClick = { movies.refresh() }
                    )
                }
            }

            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                columns = GridCells.Fixed(3),
            ) {
                items(movies.itemCount) { index ->
                    val movie = movies[index]
                    if (movie != null) {
                        MovieItem(
                            movie = movie,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    if (loadState?.refresh == LoadState.Loading) {
                        RefreshLoadingComponent(
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    if (loadState?.append == LoadState.Loading) {
                        LoadingComponent()
                    }

                    if (loadState?.refresh is LoadState.Error || loadState?.append is LoadState.Error) {
                        if (isPaginatingError && error != null) {
                            ErrorComponent(
                                error = error,
                                onRefreshClick = { movies.refresh() }
                            )
                        }
                    }
                }
            }
        }
    }
}
