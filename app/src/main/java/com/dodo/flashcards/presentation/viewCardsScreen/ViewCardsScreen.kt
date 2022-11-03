package com.dodo.flashcards.presentation.viewCardsScreen

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.presentation.common.commonModifiers.fillMaxSizeWithBackground
import com.dodo.flashcards.presentation.common.commonModifiers.flipCard
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent.CardClicked


@Composable
fun ViewCardsScreen(viewModel: ViewCardsViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        viewModel.viewState.collectAsState().value?.apply {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.9f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
/*
                FlippableTextCard(
                    modifier = Modifier.fillMaxSize(0.8f),
                    isCardFlipped = isFlipped,
                    onCardClicked = { viewModel.onEvent(CardClicked) },
                    frontTextStyle = MaterialTheme.typography.h5,
                    frontContent = "Click to flip",
                    backTextStyle = MaterialTheme.typography.h5,
                    backContent = "Flipped",
                    isEnabled = true,
                )
*/

            }
            //Buttons for mocking going to next card - needed to visualize animations to bring in a new card
            Row(
                modifier = Modifier.fillMaxSize(),
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


@Composable
fun FlippableTextCard(
    modifier: Modifier,
    isCardFlipped: Boolean,
    onCardClicked: () -> Unit,
    frontTextStyle: TextStyle,
    frontContent: String,
    backTextStyle: TextStyle,
    backContent: String,
    isEnabled: Boolean
) {
    val interactionSource = MutableInteractionSource()
    Card(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = isEnabled
            ) {
                    onCardClicked()
            }
            .flipCard(
                isAnimated = isCardFlipped,
                duration = 500,
            ),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 8.dp,
    ) {
        Box(
            modifier =
            Modifier
                .fillMaxSizeWithBackground(MaterialTheme.colors.secondaryVariant)
        ) {
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.Center),
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
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = frontContent,
                    style = frontTextStyle
                )
            }
            //"front"

            AnimatedVisibility(
                modifier = Modifier
                    .align(Alignment.Center)
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
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = backContent,
                    style = backTextStyle
                )
            }
        }
    }

}

