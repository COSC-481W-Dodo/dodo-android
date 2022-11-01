package com.dodo.flashcards.presentation.common.previews.ViewCardsScreenPreview

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.presentation.common.CenteredColumn
import com.dodo.flashcards.presentation.theme.FlashcardsAppTheme
import com.dodo.flashcards.presentation.viewCardsScreen.FlippableTextCard
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent
import java.util.EnumSet.range

class PreviewCardFlip {

    private val isCardFlipped = mutableStateOf(false)

    @Preview
    @Composable
    fun ViewCardsScreenPreview() {
        //Temporary vars for stubbing out impl of animations
        FlashcardsAppTheme {
            val interactionSource = MutableInteractionSource()
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
                    //Don't look at this its just a preliminary implementation
                    for (i in 1..4) {
                        FlippableTextCard(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .graphicsLayer {
                                    if (i != 0) {
                                        scaleX *= normalize(i.toFloat(), 4f, -36f)
                                        scaleY *= normalize(i.toFloat(), 4f, -36f)
                                        translationY -= (i * 60f) - 150f
                                    }
                                }
                                .fillMaxHeight(0.78f)
                                .fillMaxWidth(0.8f),
                            isCardFlipped = isCardFlipped.value,
                            onCardClicked = { onCardClicked() },
                            frontTextStyle = MaterialTheme.typography.h5,
                            frontContent = "Click to flip",
                            backTextStyle = MaterialTheme.typography.h5,
                            backContent = "Flipped"
                        )
                    }

                }
                //Buttons for mocking going to next card - needed to visualize animations to bring in a new card
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(150.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { /*TODO*/ }
                    ) {
                        Text("Next")
                    }
                    Button(onClick = { /*TODO*/ }) {
                        Text("Prev")
                    }
                }

            }

        }
    }

    private fun onCardClicked() {
        this@PreviewCardFlip.apply {
            isCardFlipped.value = !isCardFlipped.value
        }
    }

    private fun normalize(input: Float, rangeMax: Float, rangeMin: Float): Float =
        (input - rangeMin) / (rangeMax - rangeMin)

}

