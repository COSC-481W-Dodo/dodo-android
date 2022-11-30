package com.dodo.flashcards.presentation.viewCardsScreen.modifiers.swipe

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
@ExperimentalMaterialApi
fun rememberSwipeableCardState(maxWidth: Float, scope: CoroutineScope):
        SwipeableCardState = remember { SwipeableCardState(maxWidth, scope) }

class SwipeableCardState(
    private val maxWidth: Float,
    private val scope: CoroutineScope
) {
    companion object {
        private const val ANIMATE_BACK_MS = 400
        private const val ANIMATE_AWAY_MS = 500
        const val INITIAL_POSITION = 0f
        private const val SWIPE_TO_MULTIPLIER = 2f
    }

    private val animatableOffsetX = Animatable(INITIAL_POSITION)
    private val animatableOffsetY = Animatable(INITIAL_POSITION)
    private var _isDragging = mutableStateOf(false)

    val isDragging: Boolean
        get() = _isDragging.value

    val offsetRelativeToMaxWidthFraction: Float
        get() = (abs(offsetX) / (maxWidth / 2f)).coerceIn(0f, 1f)

    val offsetX: Float
        get() = animatableOffsetX.value
    val offsetY: Float
        get() = animatableOffsetY.value

    val targetAbsOffsetX = maxWidth / 2f

    fun acceptSwipeByAnimate(toLeft: Boolean = false) = scope.launch {
        animatableOffsetX.animateTo(
            targetValue = (if (toLeft) -1f else 1f) * (maxWidth * SWIPE_TO_MULTIPLIER),
            animationSpec = tween(ANIMATE_AWAY_MS)
        )
    }

    fun dragByAnimate(
        x: Float,
        y: Float
    ) = scope.run {
        _isDragging.value = true
        launch { animatableOffsetX.animateTo(x + animatableOffsetX.targetValue) }
        launch { animatableOffsetY.animateTo(y + animatableOffsetY.targetValue) }
    }

    fun resetPositionByAnimate() = scope.run {
        resetIsDragging()
        launch { animatableOffsetX.animateToInitialPosition() }
        launch { animatableOffsetY.animateToInitialPosition() }
    }

    fun resetPositionBySnap() = scope.launch {
        animatableOffsetX.snapToInitialPosition()
        animatableOffsetY.snapToInitialPosition()
    }

    /** Converts the current swipe horizontal offset to a value within an alternative range */
    fun convertHorizontalOffsetToNewRange(newRange: ClosedFloatingPointRange<Float>): Float {
        return newRange.run {
            val scale = endInclusive - start
            val oldRangeValue = abs(offsetX) / targetAbsOffsetX
            ((oldRangeValue * scale) + start).coerceIn(this)
        }
    }

    fun resetIsDragging() = scope.launch {
        _isDragging.value = false
    }

    private suspend fun Animatable<Float, AnimationVector1D>.animateToInitialPosition() {
        animateTo(targetValue = INITIAL_POSITION, animationSpec = tween(ANIMATE_BACK_MS))
    }

    private suspend fun Animatable<Float, AnimationVector1D>.snapToInitialPosition() {
        snapTo(targetValue = INITIAL_POSITION)
    }
}