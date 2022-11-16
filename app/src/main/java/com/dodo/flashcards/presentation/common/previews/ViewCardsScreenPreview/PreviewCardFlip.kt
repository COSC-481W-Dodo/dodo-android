package com.dodo.flashcards.presentation.common.previews.ViewCardsScreenPreview

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dodo.flashcards.presentation.theme.FlashcardsAppTheme


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
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.95f)

                        .fillMaxWidth(),
                ) {
                    /*
                    FlippableFlashCard(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxHeight(0.78f)
                            .fillMaxWidth(0.8f),
                        isCardFlipped = isCardFlipped.value,
                        onCardClicked = { onCardClicked() },
                        frontContent = "YES",
                        backContent = "YESSS",
                        flipDurationMillis = 500,
                    )

                     */


                }
                //Buttons for mocking going to next card - needed to visualize animations to bring in a new card
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
                    Button(onClick = {
                    }) {
                        Text("Prev")
                    }
                }

            }

        }
    }

    private fun normalize(input: Float, rangeMax: Float, rangeMin: Float): Float =
        (input - rangeMin) / (rangeMax - rangeMin)

}

