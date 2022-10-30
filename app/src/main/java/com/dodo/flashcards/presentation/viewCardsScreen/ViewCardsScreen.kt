package com.dodo.flashcards.presentation.viewCardsScreen

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dodo.flashcards.presentation.common.ScreenBackground
import com.dodo.flashcards.presentation.common.drawAngledBackground
import com.dodo.flashcards.presentation.common.modifiers.FillMaxSizeWithBackground
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent.CardClicked


@Composable
fun ViewCardsScreen(viewModel: ViewCardsViewModel) {
    ScreenBackground {
        viewModel.viewState.collectAsState().value?.apply {
            FlippableCard(
                isCardFlipped = isFlipped,

                size = 0.8f,

                onCardClicked = { viewModel.onEvent(CardClicked) },

                frontContent = { Text("Bruh") }) {
                Text("BRUH")
            }
        }
    }
}

fun Modifier.flipCard(
    isAnimated: Boolean,
    duration: Int,
): Modifier = composed {
    val animatedState by animateFloatAsState(
        targetValue = if (isAnimated) 180f else 0f,
        animationSpec = tween(
            durationMillis = duration,
            easing = LinearOutSlowInEasing
        ),
    )
    val value by animateFloatAsState(
        targetValue = 250f,
        animationSpec = keyframes {
            durationMillis = duration
            250.0f at 0 with LinearOutSlowInEasing
            100.0f at (duration / 2) with LinearOutSlowInEasing
            250.0f at (duration - 1) with LinearOutSlowInEasing
        }
    )

    graphicsLayer {
        rotationY = animatedState
        cameraDistance = value
    }
}

@Composable
fun FlippableCard(
    isCardFlipped: Boolean,
    size: Float,
    onCardClicked: () -> Unit,
    frontContent: @Composable () -> Unit,
    backContent: @Composable () -> Unit
) {
    val interactionSource = MutableInteractionSource()
    val value by animateFloatAsState(
        targetValue = 3f,
        animationSpec = keyframes {
            durationMillis = 500
            3.0f at 0 with LinearOutSlowInEasing
            8.0f at 250 with LinearOutSlowInEasing
            3.0f at 499 with LinearOutSlowInEasing
        }
    )
    Card(
        modifier = Modifier
            .fillMaxSize(0.8f)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onCardClicked
            }
            .flipCard(
                isAnimated = isCardFlipped,
                duration = 500
            ),
        backgroundColor = colors.background,
        elevation = value.dp
    ) {
        Box(
            modifier =
            Modifier
                .FillMaxSizeWithBackground(Color(0xff91bcb4))
                .drawAngledBackground(colors.background)
        ) {
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.Center),
                visible = !isCardFlipped,
                enter = fadeIn(
                    animationSpec = tween(
                        durationMillis = 175,
                        easing = Easing { 0f }
                    )
                ),
                exit = fadeOut(
                    animationSpec = tween(
                        durationMillis = 150,
                        easing = LinearOutSlowInEasing
                    )
                )
            ) {

                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = "Hell yeah I hate 481",
                    style = MaterialTheme.typography.h5
                )
                frontContent()
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
                        durationMillis = 175,
                        easing = Easing { 0f }
                    )
                ),
                exit = fadeOut(
                    animationSpec = tween(
                        durationMillis = 115,
                        easing = LinearOutSlowInEasing
                    )
                )
            ) {
                //"back"
                backContent()
            }
        }
    }

}

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Preview
@Composable
fun ViewCardsScreenPreview() {

    //Temporary vars for stubbing out impl of animations
    val background = colors.background
    val _primary = colors.primary
    var primary by remember { mutableStateOf(_primary) }
    val expanded = remember { mutableStateOf(false) }
    var isCardFlipped by remember { mutableStateOf(false) }
    var isTextVisible by remember { mutableStateOf(false) }
    val interactionSource = MutableInteractionSource()

}
