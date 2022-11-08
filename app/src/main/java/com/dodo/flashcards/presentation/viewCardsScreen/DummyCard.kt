package com.dodo.flashcards.presentation.viewCardsScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Undo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DummyCard(
    modifier: Modifier,
    frontContent: String,
    backgroundColor: Color,
    textColor: Color
) {
    Card(
        modifier = modifier,
        backgroundColor = backgroundColor,
        elevation = 0.dp,
        border = BorderStroke(4.dp, MaterialTheme.colors.secondary)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                IconButton(onClick = {  }) {
                    Icon(
                        imageVector = Icons.Default.Undo,
                        tint = MaterialTheme.colors.secondary,
                        contentDescription = null
                    )
                }
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = frontContent,
                color = textColor
            )
        }
    }
}