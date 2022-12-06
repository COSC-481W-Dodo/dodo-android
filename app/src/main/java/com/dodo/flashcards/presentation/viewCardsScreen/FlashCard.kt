package com.dodo.flashcards.presentation.viewCardsScreen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Undo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.presentation.common.commonModifiers.reversed
import com.dodo.flashcards.presentation.theme.Typography
import com.dodo.flashcards.presentation.viewCardsScreen.modifiers.flip.FlippableCardState
import com.dodo.flashcards.presentation.viewCardsScreen.modifiers.flip.flippableCard
import com.dodo.flashcards.presentation.viewCardsScreen.modifiers.swipe.SwipeableCardState
import com.dodo.flashcards.presentation.viewCardsScreen.modifiers.swipe.swipeableCard

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FlashCard(
    swipeableCardState: SwipeableCardState,
    flippableCardState: FlippableCardState? = null,
    colorText: Color = MaterialTheme.colors.onBackground,
    enabled: Boolean = true,
    isCardFlipped: Boolean = false,
    onClickedCard: () -> Unit = {},
    onSwipedCard: () -> Unit = {},
    text: String = ""
) {
    Box(
        modifier = Modifier
            .run {
                if (enabled && flippableCardState != null) {
                    flippableCard(
                        flippableCardState = flippableCardState,
                        onClick = onClickedCard,
                    )
                } else {
                    this
                }
            }
            .swipeableCard(
                enabled = enabled,
                swipeableCardState = swipeableCardState,
                flippableCardState = flippableCardState,
                onSwipedCard = onSwipedCard,
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp).run {
                    if (isCardFlipped) reversed()
                    else this
                },
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isCardFlipped) "ANSWER" else "QUESTION",
                style = Typography.subtitle2,
                color = MaterialTheme.colors.secondary,
            )
        }
        Text(
            text = text,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp).run {
                    if (isCardFlipped) reversed()
                    else this
                },
            textAlign = TextAlign.Center,
            color = colorText
        )
    }
}