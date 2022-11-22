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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
@ExperimentalMaterialApi
fun rememberFlippableCardState(scope: CoroutineScope):
        FlippableCardState = remember { FlippableCardState(scope) }

class FlippableCardState(
    private val scope: CoroutineScope,
) {

    companion object {
        private const val FLIP_DURATION = 300
        private const val ROTATE_MINIMUM = 0f
        private const val ROTATE_MAXIMUM = 180f
        private const val ROTATE_MIDPOINT = (ROTATE_MAXIMUM - ROTATE_MINIMUM) / 2f
        private const val MINIMUM_SCALE = 1f
        private const val MAXIMUM_SCALE = 1.05f
        private val animationSpecFull = tween<Float>(FLIP_DURATION, easing = LinearEasing)
    }

    private var animatableRotationY = Animatable(ROTATE_MINIMUM)

    val rotationY: Float
        get() = animatableRotationY.value

    /** A fraction equivalent to how rotated the card currently is. **/
    val rotationFraction: Float
        get() {
            return if (rotationY <= ROTATE_MIDPOINT) {
                rotationY / ROTATE_MIDPOINT
            } else {
                1 - ((rotationY - ROTATE_MIDPOINT) / ROTATE_MIDPOINT)
            }
        }

    fun animateFlip(transitionDuringFlip: () -> Unit) = scope.run {
        if (animatableRotationY.isRunning) return@run
        val rotateTo = when (rotationY) {
            ROTATE_MAXIMUM -> ROTATE_MINIMUM
            ROTATE_MINIMUM -> ROTATE_MAXIMUM
            else -> return@run // Prevent new animation while animating
        }
        launch {
            animatableRotationY.animateTo(targetValue = ROTATE_MIDPOINT, tween(FLIP_DURATION))
            launch(Dispatchers.Main.immediate) { transitionDuringFlip() }
            animatableRotationY.animateTo(targetValue = rotateTo, tween(FLIP_DURATION))
        }
    }

    fun resetRotationBySnap() = scope.launch {
        animatableRotationY.snapTo(0f)
    }
}

