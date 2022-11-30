package com.dodo.flashcards.presentation.viewCardsScreen.modifiers.swipe

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.presentation.viewCardsScreen.modifiers.swipe.SwipeableCardState.Companion.INITIAL_POSITION
import kotlin.math.abs

/**
 * Creates a swipeable card which may be moved on dragging and which, when the drag is released
 * either resets position or animates out of the visible area.
 * Allows the caller to recycle the view by responding to the [onSwipedCard] event.
 *
 * @param rotationDegreesMaximum The maximum degrees which a card will rotate when swiped
 * left or right.
 * @param sizeFractionMinimumDisabledCard How much smaller a disabled card will be than an
 * enabled one when [swipeableCardState] is not swiped.
 * @param swipeAcceptanceFraction Relative to the screen width, this fraction determines
 * how far the swiped composable must be dragged from the center of the screen before
 * the swipe is accepted rather than resetting to the initial position.
 */
@Composable
fun Modifier.swipeableCard(
    swipeableCardState: SwipeableCardState,
    enabled: Boolean = true,
    colorStroke: Color = MaterialTheme.colors.secondary,
    colorBackground: Color = MaterialTheme.colors.primaryVariant,
    shape: Shape = RoundedCornerShape(16.dp),
    elevationShadow: Dp = 8.dp,
    strokeWidth: Dp = 2.dp,
    rotationDegreesMaximum: Int = 20,
    sizeFractionMinimumDisabledCard: Float = 0.9f,
    swipeAcceptanceFraction: Float = 0.4f,
    onSwipedCard: () -> Unit = {}
): Modifier = composed {
    swipeableCardState.run {
        val swipeAcceptanceMin = (targetAbsOffsetX * swipeAcceptanceFraction)
        if (enabled) {
            dragReleasable(
                translationX = offsetX,
                translationY = offsetY,
                rotationZ = convertHorizontalOffsetToNewRange(0f..rotationDegreesMaximum.toFloat()).let {
                    if (offsetX < 0) it * -1 else it
                },
                onDrag = {
                    if (it != Offset.Unspecified) {
                        dragByAnimate(it.x, it.y)
                    }
                },
                onDragReleased = {
                    println("Drag releasable event: $offsetX")
                    when {
                        abs(offsetX) > swipeAcceptanceMin -> {
                            acceptSwipeByAnimate(toLeft = offsetX < INITIAL_POSITION)
                                .invokeOnCompletion {
                                    onSwipedCard()
                                }
                        }
                        else -> { resetPositionByAnimate()}
                    }
                },
            )
        } else {
            scale(
                scale = convertHorizontalOffsetToNewRange(sizeFractionMinimumDisabledCard..1f)
            ).graphicsLayer {
                //  translationY = -(convertHorizontalOffsetToNewRange(-80f..0f))
            }
        }
            .border(
                width = strokeWidth,
                color = colorStroke.copy(
                    alpha = if (enabled) offsetRelativeToMaxWidthFraction else 0f
                ),
                shape = shape
            )
            .shadow(shape = shape, elevation = elevationShadow)
            .clip(shape)
            .background(colorBackground)
            .fillMaxSize()
    }
}

private fun Modifier.dragReleasable(
    translationX: Float = 0f,
    translationY: Float = 0f,
    rotationZ: Float = 0f,
    onDrag: (Offset) -> Unit,
    onDragReleased: () -> Unit,
): Modifier = composed {
    pointerInput(Unit) {
        detectDragGestures(
            onDrag = { change, dragAmount ->
                onDrag(dragAmount)
                change.consume()

            },
            onDragEnd = {  onDragReleased() }
        )
    }.graphicsLayer {
/*
        println("yo here $translationX x and y is $translationY")
*/
        this.translationX = translationX
        this.translationY = translationY
        this.rotationZ = rotationZ
    }
}
