package com.radmanhayati.cafebazar.presentation

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.paging.compose.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.radmanhayati.cafebazar.R
import com.radmanhayati.cafebazar.domain.Movie
import com.radmanhayati.cafebazar.ui.theme.*

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
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(movies) { movie ->
                    if (movie != null) {
                        MovieItem(
                            movie = movie,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                /* item {
                    if (movies.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }*/
                val loadState = movies.loadState.mediator
                item {
                    if (loadState?.refresh == LoadState.Loading) {
                        Column(
                            modifier = Modifier
                                .fillParentMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(8.dp),
                                text = "Refresh Loading..."
                            )

                            CircularProgressIndicator(color = Color.Green)
                        }
                    }

                    if (loadState?.append == LoadState.Loading) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator(color = Color.Green)
                        }
                    }

                    if (loadState?.refresh is LoadState.Error || loadState?.append is LoadState.Error) {
                        val isPaginatingError =
                            (loadState.append is LoadState.Error) || movies.itemCount > 1
                        val error = if (loadState.append is LoadState.Error)
                            (loadState.append as LoadState.Error).error
                        else
                            (loadState.refresh as LoadState.Error).error

                        if (isPaginatingError) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier
                                        .weight(3f),
                                    text = error.message ?: error.toString(),
                                    textAlign = TextAlign.Start,
                                )
                                Button(
                                    modifier = Modifier
                                        .weight(1f),
                                    onClick = {
                                        movies.refresh()
                                    },
                                    content = {
                                        Text(text = "Refresh", color = red)
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = darkGray),
                                    shape = RoundedCornerShape(4.dp),
                                    border = BorderStroke(3.dp, lightGray)
                                )

                            }
                        } else {
                            Column(
                                modifier = Modifier.fillParentMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Image(
                                    modifier = Modifier
                                        .size(64.dp),
                                    painter = painterResource(id = R.drawable.src_sadface),
                                    contentDescription = null
                                )

                                Text(
                                    modifier = Modifier
                                        .padding(8.dp),
                                    text = error.message ?: error.toString(),
                                    color = lightBlue,
                                    textAlign = TextAlign.Center,
                                )
                                Button(
                                    onClick = {
                                        movies.refresh()
                                    },
                                    content = {
                                        Text(text = "Refresh", color = Color.White)
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = blue),
                                    shape = RoundedCornerShape(20.dp)
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}