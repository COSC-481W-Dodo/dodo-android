package com.dodo.flashcards.presentation.viewCardsScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Undo
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
    modifier: Modifier = Modifier,
    onCardClicked: () -> Unit = {},
    onClickedPrevious: () -> Unit = {},
    flipDurationMillis: Int,
    frontContent: String,
    backContent: String,
    backgroundColor: Color,
    textColor: Color,
) {
    val interactionSource = MutableInteractionSource()
    Card(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
            ) {
                onCardClicked()
            },
        /*
    .flipCard(
        isAnimated = isCardFlipped,
        duration = flipDurationMillis,
    )
    ,
         */
        backgroundColor = backgroundColor,
        elevation = 0.dp,
        border = BorderStroke(4.dp, MaterialTheme.colors.secondary)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                IconButton(onClick = { onClickedPrevious() }) {
                    Icon(
                        imageVector = Icons.Default.Undo,
                        tint = MaterialTheme.colors.secondary,
                        contentDescription = null
                    )
                }
            }
            Text(
                text = if (isCardFlipped) backContent else frontContent,
                modifier = Modifier.align(Alignment.Center),
                color = textColor
            )

            /*
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

             */

            //     }

            /*
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
            */

            //}
        }
    }
}

