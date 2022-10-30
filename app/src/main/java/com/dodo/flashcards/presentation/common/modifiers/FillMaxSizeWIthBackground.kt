package com.dodo.flashcards.presentation.common.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color

fun Modifier.FillMaxSizeWithBackground(backgroundColor: Color): Modifier  = composed {
    fillMaxSize(1f).background(backgroundColor)
}