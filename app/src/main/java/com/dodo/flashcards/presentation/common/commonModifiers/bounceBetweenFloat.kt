package com.dodo.flashcards.presentation.common.commonModifiers

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer

fun Modifier.bounceBetweenFloat(
    animationTrigger: Boolean,
    restingValue: Float,
    targetValue: Float,
    durationMillis: Int,
    onAnimationComplete: () -> Unit,
): Modifier = composed {

    val animation by remember { mutableStateOf(Animatable(restingValue)) }

    //Launch LaunchedEffect on trigger
    if (animationTrigger) {

        LaunchedEffect(Unit) {

            animation.animateTo(
                targetValue = targetValue,
                animationSpec = tween(
                    durationMillis = durationMillis / 2,
                    easing = LinearEasing
                )
            )

            animation.animateTo(
                targetValue = restingValue,
                animationSpec = tween(
                    durationMillis = durationMillis/ 2,
                    easing = LinearEasing
                )
            )

            //Switch animationTrigger back to false
            onAnimationComplete()
        }
    }
    graphicsLayer {
        scaleX *= animation.value
        scaleY *= animation.value
    }

}
