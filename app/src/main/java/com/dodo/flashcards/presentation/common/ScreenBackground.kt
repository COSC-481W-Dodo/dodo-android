package com.dodo.flashcards.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.presentation.theme.DarkColors

@Composable
fun ScreenBackground(
    scrollEnabled: Boolean = true,
    padding: Dp = 16.dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    val surfaceColor = MaterialTheme.colors.surface
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(surfaceColor)
            .run {
                if (scrollEnabled) {
                    verticalScroll(rememberScrollState())
                } else {
                    this
                }
            }
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
    ) {
        content()
    }
}

