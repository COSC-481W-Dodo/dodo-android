package com.dodo.flashcards.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import com.dodo.flashcards.presentation.theme.DarkColors

@Composable
fun ScreenBackground(
    content: @Composable () -> Unit
) {
    val backgroundColor = MaterialTheme.colors.background
    val surfaceColor = MaterialTheme.colors.surface
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(surfaceColor)
            .drawBehind {
                val path = Path().apply {
                    moveTo(0f, size.height * 0.05f)
                    lineTo(size.width, 0.2f * size.height)
                    lineTo(size.width, 0.95f * size.height)
                    lineTo(0f, 0.8f * size.height)
                }
                drawPath(
                    path = path,
                    color = backgroundColor
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        content()
    }
}