package com.dodo.flashcards.presentation.common.commonModifiers

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
@ExperimentalMaterialApi
fun rememberSwipeState(maxWidth: Float, maxHeight: Float):
        Swipe = remember { Swipe(maxWidth, maxHeight) }

open class Swipe(val maxWidth: Float, val maxHeight: Float) {
    val offsetX = Animatable(0f)
    val offsetY = Animatable(0f)

    fun reset(scope: CoroutineScope) = scope.launch {
        launch { offsetX.animateTo(targetValue = 0f, animationSpec = tween(400)) }
        launch { offsetY.animateTo(targetValue = 0f, animationSpec = tween(400)) }
    }

    fun accepted(
        scope: CoroutineScope,
        onDragAccepted: () -> Unit
    ) = scope.launch {
        offsetX.animateTo(targetValue = (maxWidth * 2), animationSpec = tween(300))
//        onDragAccepted()
        offsetX.snapTo(targetValue = 0f)
        offsetY.snapTo(targetValue = 0f)
    }

    fun rejected(scope: CoroutineScope) = scope.launch {
        offsetX.animateTo(targetValue = -(maxWidth * 2), animationSpec = tween(300))
        offsetX.snapTo(targetValue = 0f)
        offsetY.snapTo(targetValue = 0f)
    }

    fun drag(
        scope: CoroutineScope,
        x: Float,
        y: Float
    ) = scope.launch {
        launch { offsetX.animateTo(x) }
        launch { offsetY.animateTo(y) }
    }
}

fun Modifier.swipe(
    state: Swipe,
    onDragReset: () -> Unit = {},
    onDragAccepted: () -> Unit
): Modifier = composed {
    val scope = rememberCoroutineScope()
    val stopDrag = remember {
        mutableStateOf(value = false)
    }
    pointerInput(Unit) {
        detectDragGestures(
            onDragEnd = {
                if (!stopDrag.value) {
                    when {
                        abs(state.offsetX.targetValue) < (state.maxWidth / 8) -> {
                            state.reset(scope).invokeOnCompletion { onDragReset() }
                        }
                        state.offsetX.targetValue < 0 -> {
                            stopDrag.value = true
                            scope.launch {
                                state
                                    .rejected(scope)
                                    .invokeOnCompletion {
                                        scope.launch {
                                            onDragAccepted()
                                            stopDrag.value = false
                                        }
                                    }
                            }
                        }
                        else -> {
                            stopDrag.value = true
                            scope.launch {
                                state.accepted(scope, onDragAccepted)
                                    .invokeOnCompletion {
                                        scope.launch {
                                            onDragAccepted()
                                            stopDrag.value = false
                                        }
                                    }
                            }
                        }
                    }
                }
            },
            onDragStart = { },
            onDrag = { change, dragAmount ->
                if (!stopDrag.value) {
                    val original = Offset(state.offsetX.targetValue, state.offsetY.targetValue)
                    val summed = original + dragAmount
                    val newValue = Offset(
                        x = summed.x.coerceIn(-state.maxWidth, state.maxWidth),
                        y = summed.y.coerceIn(-state.maxHeight, state.maxHeight)
                    )
                    change.consume()
                    state.drag(scope, newValue.x, newValue.y)
                }
            }
        )
    }.graphicsLayer {
        translationX = state.offsetX.value
        translationY = state.offsetY.value
        rotationZ = (state.offsetX.value / 60).coerceIn(-40f, 40f)
        scaleX = ((state.maxWidth - abs(state.offsetX.value / 2f)) / state.maxWidth).coerceAtMost(1f)
        scaleY = ((state.maxWidth - abs(state.offsetX.value / 2f)) / state.maxWidth).coerceAtMost(1f)
    }
}

