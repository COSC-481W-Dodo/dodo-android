package com.dodo.flashcards.presentation.viewCardsScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.presentation.common.commonModifiers.flipCard

@Composable
fun FlippableFlashCard(
    isCardFlipped: Boolean,
    flipDurationMillis: Int,
    frontContent: String,
    backContent: String,
    backgroundColor: Color = MaterialTheme.colors.surface,
    textColor: Color = MaterialTheme.colors.onSurface,
    modifier: Modifier = Modifier,
) {
    val interactionSource = MutableInteractionSource()
    Card(
        modifier = modifier,
/*
            .clickable(
                interactionSource = interactionSource,
                indication = null,
            ) {
                onCardClicked()
            }
            .flipCard(
                isAnimated = isCardFlipped,
                duration = flipDurationMillis,
            ),
*/
        backgroundColor = backgroundColor,
        elevation = 8.dp,
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.Center),
                visible = !isCardFlipped,
                enter = fadeIn(
                    animationSpec = tween(
                        durationMillis = (0.45f * flipDurationMillis).toInt(),
                        easing = { 0f }
                    )
                ),
                exit = fadeOut(
                    animationSpec = tween(
                        durationMillis = (0.45f * flipDurationMillis).toInt(),
                        easing = { 0f }
                    )
                )
            ) {
                Text(
                    text = frontContent,
                    color = textColor
                )
            }

            AnimatedVisibility(
                modifier = Modifier
                    .align(Alignment.Center)
                    .graphicsLayer {
                        rotationY = 180f
                    },
                visible = isCardFlipped,
                enter = fadeIn(
                    animationSpec = tween(
                        durationMillis = (0.45f * flipDurationMillis).toInt(),
                        easing = { 0f }
                    )
                ),
                exit = fadeOut(
                    animationSpec = tween(
                        durationMillis = (0.45f * flipDurationMillis).toInt(),
                        easing = { 0f }
                    )
                )
            ) {
                Text(
                    text = backContent,
                    color = textColor
                )
            }
        }
    }
}

