package com.dodo.flashcards.presentation.viewCardsScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.presentation.common.commonModifiers.fillMaxSizeWithBackground
import com.dodo.flashcards.presentation.common.commonModifiers.flipCard

@Composable
fun FlippableFlashCard(
    isCardFlipped: Boolean,
    onCardClicked: () -> Unit,
//    frontTextStyle: TextStyle,
    frontContent: String,
    //   backTextStyle: TextStyle,
    backContent: String,
    modifier: Modifier = Modifier,
    //  isEnabled: Boolean
) {
    val interactionSource = MutableInteractionSource()

    Card(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                // enabled = isEnabled
            ) {
                onCardClicked()
            }
            .flipCard(
                isAnimated = isCardFlipped,
                duration = 500,
            ),
        backgroundColor = MaterialTheme.colors.secondary,
        elevation = 8.dp,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = !isCardFlipped,
                enter = fadeIn(
                    animationSpec = tween(
                        durationMillis = 225,
                        easing = Easing { 0f }
                    )
                ),
                exit = fadeOut(
                    animationSpec = tween(
                        durationMillis = 225,
//                        easing = FastOutLinearInEasing
                        easing = Easing { 0f }
                    )
                )
            ) {

                Text(
                    text = frontContent,
                    //        style = frontTextStyle
                )
            }
            //"front"

            AnimatedVisibility(
                modifier = Modifier
                    .graphicsLayer {
                        rotationY = 180f
                    },
                visible = isCardFlipped,
                enter = fadeIn(
                    animationSpec = tween(
                        durationMillis = 225,
                        easing = Easing { 0f }
                    )
                ),
                exit = fadeOut(
                    animationSpec = tween(
                        durationMillis = 225,
//                        easing = FastOutLinearInEasing
                        easing = Easing { 0f }
                    )
                )
            ) {
                //"back"
                Text(
                    text = backContent,
                    //  style = backTextStyle
                )
            }
        }
    }
}

