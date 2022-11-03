package com.dodo.flashcards.presentation.common.previews.ViewCardsScreenPreview

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import com.dodo.flashcards.presentation.common.commonModifiers.bounceBetweenFloat
import com.dodo.flashcards.presentation.theme.FlashcardsAppTheme
import com.dodo.flashcards.presentation.viewCardsScreen.FlippableTextCard


class PreviewCardFlip {
    private val isCardFlipped = mutableStateOf(false)
    val isScaled = mutableStateOf(false)

    //Functions to extract to onEvent funs in viewmodel
    private fun onCardClicked() {
        this@PreviewCardFlip.apply {
            isCardFlipped.value = !isCardFlipped.value

            isScaled.value = !isScaled.value
        }
    }

    private fun onAnimatableComplete() {
        this@PreviewCardFlip.apply {
            isScaled.value = !isScaled.value
        }
    }




    @Preview
    @Composable
    fun ViewCardsScreenPreview() {

        val interactionSource = MutableInteractionSource()
        val animation by remember { mutableStateOf(Animatable(1f)) }
        //Temporary vars for stubbing out impl of animations
        FlashcardsAppTheme {
/*
            if (isScaled.value) {
                LaunchedEffect(Unit) {
                    animation.animateTo(
                        targetValue = 1.1f,
                        animationSpec = tween(
                            durationMillis = 250,
                            easing = LinearEasing
                        )
                    )
                    animation.animateTo(
                        targetValue = 1.0f,
                        animationSpec = tween(
                            durationMillis = 250,
                            easing = LinearEasing
                        )
                    )
                    onAnimatableComplete()
                }

            }
*/

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.95f)

                        .bounceBetweenFloat(
                            animationTrigger = isScaled.value,
                            restingValue = 1f,
                            targetValue = 1.1f,
                            durationMillis = 500,
                            onAnimationComplete = { onAnimatableComplete() }
                        )
                        .fillMaxWidth(),
                ) {
                    FlippableTextCard(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .graphicsLayer {
                                scaleX *= animation.value
                                scaleY *= animation.value
                            }
                            .fillMaxHeight(0.78f)
                            .fillMaxWidth(0.8f),
                        isCardFlipped = isCardFlipped.value,
                        onCardClicked = { onCardClicked() },
                        frontTextStyle = MaterialTheme.typography.h5,
                        frontContent = "YES",
                        backTextStyle = MaterialTheme.typography.h5,
                        backContent = "YESSS",
                        isEnabled = true,
                    )

                }
                //Buttons for mocking going to next card - needed to visualize animations to bring in a new card
/*
                Row(
                    modifier = Modifier
                        .fillMaxSize(),

                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(

                        onClick = { }
                    ) {
                        Text("Next")
                    }
                    Button(onClick = { */
/*TODO*//*
 }) {
                        Text("Prev")
                    }
                }
*/

            }

        }
    }

    private fun normalize(input: Float, rangeMax: Float, rangeMin: Float): Float =
        (input - rangeMin) / (rangeMax - rangeMin)

}

