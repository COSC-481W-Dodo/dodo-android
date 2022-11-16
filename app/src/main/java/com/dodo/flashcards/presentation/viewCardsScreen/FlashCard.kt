package com.dodo.flashcards.presentation.viewCardsScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Undo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.presentation.theme.Typography
import com.dodo.flashcards.presentation.viewCardsScreen.modifiers.SwipeableCardState
import com.dodo.flashcards.presentation.viewCardsScreen.modifiers.swipeableCard

@Composable
fun FlashCard(
    swipeableCardState: SwipeableCardState,
    colorText: Color = MaterialTheme.colors.onBackground,
    enabled: Boolean = true,
    isCardFlipped: Boolean = false,
    onClickedCard: () -> Unit = {},
    onClickedPrevious: () -> Unit = {},
    onSwipedCard: () -> Unit = {},
    text: String = ""
) {
    Box(
        modifier = Modifier
            .swipeableCard(
                enabled = enabled,
                swipeableCardState = swipeableCardState,
                onSwipedCard = onSwipedCard
            )
            .clickable(
                enabled = enabled,
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClickedCard
            )
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
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
                .padding(16.dp),
            textAlign = TextAlign.Center,
            color = colorText
        )
    }
}