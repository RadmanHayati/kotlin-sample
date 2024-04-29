package com.radmanhayati.cafebazar.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.radmanhayati.cafebazar.domain.Movie
import com.radmanhayati.cafebazar.ui.theme.CafebazarTheme
import com.radmanhayati.cafebazar.ui.theme.blue
import com.radmanhayati.cafebazar.ui.theme.darkGray
import com.radmanhayati.cafebazar.ui.theme.lightGray
import com.radmanhayati.cafebazar.ui.theme.red

@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        AsyncImage(
            model = movie.posterPath,
            contentDescription = movie.name,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .weight(1f)
                .height(150.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = movie.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun BeerItemPreview() {
    CafebazarTheme {
        MovieItem(
            movie = Movie(
                id = 1,
                name = "name",
                originalTitle = "sd",
                voteAverage = 4.1,
                voteCount = 200,
                overview = "overw",
                originalLanguage = "s",
                posterPath = "s"
            )
        )
    }
}