package com.dodo.flashcards.presentation.common.commonModifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer

fun Modifier.reversed(): Modifier = composed {
    graphicsLayer {
        rotationY = 180f
    }
}