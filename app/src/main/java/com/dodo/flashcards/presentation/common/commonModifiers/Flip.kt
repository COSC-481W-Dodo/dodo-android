package com.dodo.flashcards.presentation.common.commonModifiers

import android.content.ContentValues.TAG
import android.graphics.drawable.GradientDrawable
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.graphicsLayer
import com.google.api.Distribution.BucketOptions.Linear
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
@ExperimentalMaterialApi
fun rememberFlipState(): FlipState = remember { FlipState() }

open class FlipState(
    val bounceFrom: Float = 1f,
    val bounceTo: Float = 1.03f,
) {
    private val ROTATE_MIN = 0f
    private val ROTATE_MAX = 180f
    private var rotateTo = ROTATE_MAX

    private var isFlipped = false

    val scale = Animatable(0f)
    val rotationY = Animatable(0f)
    private val alpha = Animatable(1f)

    fun flip(
        scope: CoroutineScope,
        transitionContent: () -> Unit
    ) = scope.launch {
        Log.d(TAG, "RotateTo is: $rotateTo")
        Log.d(TAG, "IsFlipped is: $isFlipped")

        launch {
            rotateTo = if (isFlipped) 0f else 180f
            rotationY.animateTo(rotateTo, tween(500, easing = LinearEasing))
            isFlipped = !isFlipped
        }

        //optionally bounce scale bounceFrom -> bounceTo -> bounceFrom
        launch {
            scale.animateTo(bounceTo, tween(250, easing = LinearEasing))
            scale.animateTo(bounceFrom, tween(250, easing = LinearEasing))
        }

        //Animate an alpha value from 1 -> 0 -> 1
        launch {
            alpha.animateTo(0f, tween(250, easing = LinearEasing))
            transitionContent()
            alpha.animateTo(1f, tween(250, easing = LinearEasing))
        }
    }

    //unused
    fun reset(scope: CoroutineScope) = scope.launch {
        launch { alpha.snapTo(1f) }
        launch { rotationY.snapTo(1f) }
        launch { scale.snapTo(1f) }
    }
}

fun Modifier.flip(
    state: FlipState,
    onClick: () -> Unit,
    transitionContent: () -> Unit
): Modifier = composed {
    val scope = rememberCoroutineScope()

    Modifier
        .clickable(
            interactionSource = MutableInteractionSource(),
            indication = null,
        ) {
            scope.launch {
                state
                    .flip(this, transitionContent)
                    .invokeOnCompletion {
                        scope.launch {
                            onClick()
                        }
                    }
            }
        }
        .graphicsLayer {
            rotationY = state.rotationY.value
            scaleX *= state.scale.value
            scaleY *= state.scale.value
            cameraDistance = 300f
        }

}