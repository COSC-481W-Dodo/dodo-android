package com.dodo.flashcards.presentation.viewCardsScreen.modifiers.flip

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.api.Distribution.BucketOptions.Linear
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
@ExperimentalMaterialApi
fun rememberFlippableCardState(scope: CoroutineScope):
        FlippableCardState = remember { FlippableCardState(scope) }

class FlippableCardState(
    private val scope: CoroutineScope,
) {

    companion object {
        private val FLIP_DURATION = 300
        private val ROTATE_MINIMUM = 0f
        private val ROTATE_MAXIMUM = 180f
        private val MINIMUM_SCALE = 1f
        private val MAXIMUM_SCALE = 1.05f
        private val animationSpecFull = tween<Float>(FLIP_DURATION, easing = LinearEasing)
        private val animationSpecHalf = tween<Float>(FLIP_DURATION / 2, easing = LinearEasing)
    }

    private var animatableRotationY = Animatable(ROTATE_MINIMUM)
    private var animatableStrokeAlpha = Animatable(0f)

    val rotationY: Float get() = animatableRotationY.value

    val strokeAlpha: Float get() = animatableStrokeAlpha.value

    fun animateFlip(transitionDuringFlip: () -> Unit) = scope.run {
        launch {
            val rotateTo = if (rotationY == 180f) ROTATE_MINIMUM else ROTATE_MAXIMUM
            animatableRotationY.animateTo(targetValue = ROTATE_MAXIMUM / 2, animationSpecHalf)
            launch { transitionDuringFlip() }
            animatableRotationY.animateTo(targetValue = rotateTo, animationSpecHalf)
        }
        launch {
            animatableStrokeAlpha.animateTo(0.8f, animationSpecHalf)
            animatableStrokeAlpha.animateTo(0f, animationSpecHalf)
        }
    }

    fun resetRotationBySnap() = scope.launch {
        animatableRotationY.snapTo(0f)
    }
}

