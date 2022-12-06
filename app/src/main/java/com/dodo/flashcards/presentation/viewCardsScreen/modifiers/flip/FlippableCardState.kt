package com.dodo.flashcards.presentation.viewCardsScreen.modifiers.flip

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.api.Distribution.BucketOptions.Linear
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
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
        private val flipAnimationSpec = tween<Float>(FLIP_DURATION / 2, easing = LinearEasing)
    }

    private var animatableRotationY = Animatable(ROTATE_MINIMUM)

    var isFlipping: MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)

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
        val rotateTo = when (rotationY) {
            ROTATE_MAXIMUM -> ROTATE_MINIMUM
            ROTATE_MINIMUM -> ROTATE_MAXIMUM
            else -> return@run // Prevent new animation while animating
        }
        println("yo is flipping is $isFlipping")
        if (isFlipping.value == true) return@run
        isFlipping.value = true
        launch(Dispatchers.Main) {
            animatableRotationY.animateTo(targetValue = ROTATE_MIDPOINT, flipAnimationSpec)
            transitionDuringFlip()
            animatableRotationY.animateTo(targetValue = rotateTo, flipAnimationSpec)
            println("here updating is flipping")

        }.invokeOnCompletion { isFlipping.value = false }
    }

    fun resetRotationBySnap() = scope.launch {
        animatableRotationY.snapTo(0f)
    }
}

