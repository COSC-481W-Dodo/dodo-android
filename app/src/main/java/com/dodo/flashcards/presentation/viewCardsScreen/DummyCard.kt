package com.dodo.flashcards.presentation.viewCardsScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Undo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.presentation.theme.Typography

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
        border = BorderStroke(1.dp, MaterialTheme.colors.secondary.copy(alpha = 0.5f))
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Undo,
                        tint = MaterialTheme.colors.secondary,
                        contentDescription = null
                    )
                }
                Text(
                    text = "QUESTION",
                    style = Typography.subtitle2,
                    color = MaterialTheme.colors.secondary,
                )
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = frontContent,
                color = textColor
            )
        }
    }
}