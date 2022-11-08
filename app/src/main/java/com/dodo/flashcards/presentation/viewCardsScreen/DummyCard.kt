package com.dodo.flashcards.presentation.viewCardsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
        elevation = 8.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = frontContent,
                color = textColor
            )
        }
    }
}