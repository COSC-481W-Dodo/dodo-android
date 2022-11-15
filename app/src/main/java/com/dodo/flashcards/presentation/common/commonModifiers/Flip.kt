package com.dodo.flashcards.presentation.common.commonModifiers

import android.graphics.drawable.GradientDrawable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.graphicsLayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
@ExperimentalMaterialApi
fun rememberFlipState(isFlipped: Boolean): FlipState = remember { FlipState(isFlipped) }

open class FlipState(
    val isFlipped: Boolean,
    bounceFrom: Float = 0f,
    bounceTo: Float = 0f,
){
    val rotationY = Animatable(0f)
    val bounceFrom = Animatable(bounceFrom)
    val bounceTo = Animatable(bounceTo)

    fun rotateTo(
        scope: CoroutineScope,
    ) = scope.launch {
        launch { rotationY.animateTo(180f, tween(500)) }
    }

    fun rotateBack(scope: CoroutineScope) = scope.launch {
        launch { rotationY.animateTo(0f, tween(500)) }
    }
}

fun Modifier.flip(
    state: FlipState,
    onClick:() -> Unit
): Modifier = composed {
    val scope = rememberCoroutineScope()
    
    clickable(
        interactionSource = MutableInteractionSource(),
        indication = null,
    ) {
        if (!state.isFlipped) {
            scope.launch {
                state.rotateTo(this)
                    .invokeOnCompletion {
                        scope.launch {
                            onClick()
                        }
                    }
            }
        }
        if (state.isFlipped) {
            scope.launch {
                state.rotateBack(this)
                    .invokeOnCompletion {
                        scope.launch {
                            onClick()
                        }
                    }
            }
        }

    }
        .graphicsLayer {
            rotationY = state.rotationY.value
        }

}