package com.dodo.flashcards.presentation.viewCardsScreen.modifiers.flip

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun Modifier.flippableCard(
    flippableCardState: FlippableCardState,
    strokeWidth: Dp = 2.dp,
    colorStroke: Color = MaterialTheme.colors.secondary,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp),
    enabled: Boolean,
    onClick: () -> Unit = {},
) = composed {
    when (enabled) {
        false -> Modifier
        else -> clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        ) {
            flippableCardState.animateFlip(onClick)
        }
            .graphicsLayer {
                rotationY = flippableCardState.rotationY
                cameraDistance = 150f
            }
            .border(
                width = strokeWidth,
                shape = shape,
                color = colorStroke.copy(
                    alpha = flippableCardState.strokeAlpha
                )
            )
    }

}