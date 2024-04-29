package com.radmanhayati.cafebazar.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.radmanhayati.cafebazar.ui.theme.darkGray
import com.radmanhayati.cafebazar.ui.theme.lightGray
import com.radmanhayati.cafebazar.ui.theme.red

@Composable
fun ErrorComponent(
    modifier: Modifier = Modifier,
    error: Throwable,
    onRefreshClick: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
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
                onRefreshClick()
            },
            content = {
                Text(text = "Refresh", color = red)
            },
            colors = ButtonDefaults.buttonColors(containerColor = darkGray),
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(3.dp, lightGray)
        )

    }
}