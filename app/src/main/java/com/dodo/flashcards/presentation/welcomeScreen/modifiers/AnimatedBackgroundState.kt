package com.dodo.flashcards.presentation.welcomeScreen.modifiers

import androidx.compose.animation.core.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/*
@Composable
@ExperimentalMaterialApi
fun rememberAnimatedBackgroundState(scope: CoroutineScope): AnimatedBackgroundState =
    remember { AnimatedBackgroundState(scope) }

class AnimatedBackgroundState(private val scope: CoroutineScope) {
    val animatableSweepAngle: InfiniteTransition
        get() = rememberInfiniteTransition()

    val sweepAngle: Float get() = animatableSweepAngle.value

    fun animateSweepAngle() = scope.launch {
        animatableSweepAngle.animateTo(100f, animationSpec = tween(2000))
    }

@Composable
fun Modifier.animateBackground(
    black: Color = MaterialTheme.colors.onBackground

): Modifier = composed {
    drawBehind {
        drawArc(
            color = black,
            startAngle = 0f,
            sweepAngle = 60f,
            useCenter = true,
        )
    }
}

 */