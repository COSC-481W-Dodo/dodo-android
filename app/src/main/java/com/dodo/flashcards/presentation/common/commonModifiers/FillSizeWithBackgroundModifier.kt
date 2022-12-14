package com.dodo.flashcards.presentation.common.commonModifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

fun Modifier.fillMaxSizeWithBackground(
    backgroundColor: Color
): Modifier = fillMaxSize().background(backgroundColor)