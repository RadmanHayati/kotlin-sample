package com.radmanhayati.cafebazar.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.radmanhayati.cafebazar.R
import com.radmanhayati.cafebazar.ui.theme.blue
import com.radmanhayati.cafebazar.ui.theme.lightBlue

@Composable
fun RefreshErrorComponent(
    modifier: Modifier = Modifier,
    error: Throwable,
    onRefreshClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
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
            onClick = onRefreshClick,
            content = {
                Text(text = stringResource(R.string.refresh), color = Color.White)
            },
            colors = ButtonDefaults.buttonColors(containerColor = blue),
            shape = RoundedCornerShape(20.dp)
        )

    }
}