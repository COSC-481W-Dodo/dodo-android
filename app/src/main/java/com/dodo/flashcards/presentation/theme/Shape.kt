package com.dodo.flashcards.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.dp

data class CustomShapes(
    val topRoundedCornerShape: RoundedCornerShape,
    val bottomRoundedCornerShape: RoundedCornerShape
)

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),

    //TODO move this to a custom shape class when we figure that out
    large = RoundedCornerShape(
        topStart = 30.dp,
        topEnd = 30.dp,
    ),
)
