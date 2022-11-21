package com.dodo.flashcards.presentation.viewCardsScreen

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.presentation.common.commonModifiers.reversed
import com.dodo.flashcards.presentation.theme.Typography
import com.dodo.flashcards.presentation.viewCardsScreen.modifiers.flip.FlippableCardState
import com.dodo.flashcards.presentation.viewCardsScreen.modifiers.flip.flippableCard
import com.dodo.flashcards.presentation.viewCardsScreen.modifiers.swipe.SwipeableCardState
import com.dodo.flashcards.presentation.viewCardsScreen.modifiers.swipeableCard

@Composable
fun FlashCard(
    swipeableCardState: SwipeableCardState,
    flippableCardState: FlippableCardState,
    colorText: Color = MaterialTheme.colors.onBackground,
    enabled: Boolean = true,
    isDummy: Boolean = false,
    isCardFlipped: Boolean = false,
    onClickedCard: () -> Unit = {},
    onClickedPrevious: () -> Unit = {},
    onSwipedCard: () -> Unit = {},
    text: String = ""
) {
    Box(
        modifier = Modifier
            .flippableCard(
                enabled = enabled,
                flippableCardState = flippableCardState,
                onClick = onClickedCard,
            )
            .swipeableCard(
                enabled = enabled,
                swipeableCardState = swipeableCardState,
                onSwipedCard = onSwipedCard,
                isDummy = isDummy
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp).run {
                    if (isCardFlipped) reversed()
                    else this
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onClickedPrevious() }) {
                Icon(
                    imageVector = Icons.Default.Undo,
                    tint = MaterialTheme.colors.secondary,
                    contentDescription = null
                )
            }
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