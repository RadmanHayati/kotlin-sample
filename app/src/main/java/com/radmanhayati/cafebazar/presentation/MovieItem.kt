package com.radmanhayati.cafebazar.presentation

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)
        ) {
            AsyncImage(
                model = movie.posterPath,
                contentDescription = movie.name,
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = movie.name,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
fun BeerItemPreview() {
    CafebazarTheme {
        Column {
            Button(
                onClick = {

                },
                content = {
                    Text(text = "Refresh", color = red)
                },
                colors = ButtonDefaults.buttonColors(containerColor = darkGray),
                shape = RoundedCornerShape(4.dp),
                border = BorderStroke(1.dp, lightGray)
            )
            Button(
                onClick = {

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