package com.dodo.flashcards.presentation.common.commonModifiers

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer

fun Modifier.flipCard(
    isAnimated: Boolean,
    duration: Int,
): Modifier = composed {
    val animatedYRotation by animateFloatAsState(
        targetValue = if (isAnimated) 180f else 0f,
        animationSpec = tween(
            durationMillis = duration,
            easing = LinearEasing
        ),
    )
    graphicsLayer {
        rotationY = animatedYRotation
        cameraDistance = 500f
    }
}
