package com.dodo.flashcards.presentation.viewCardsScreen.subscreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.Undo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.architecture.EventReceiver
import com.dodo.flashcards.domain.models.Flashcard
import com.dodo.flashcards.presentation.viewCardsScreen.FlashCard
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent.*
import com.dodo.flashcards.presentation.viewCardsScreen.modifiers.flip.rememberFlippableCardState
import com.dodo.flashcards.presentation.viewCardsScreen.modifiers.swipe.rememberSwipeableCardState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardsLoaded(
    currentCard: Flashcard?,
    nextCard: Flashcard?,
    isFlipped: Boolean,
    isShuffled: Boolean,
    eventReceiver: EventReceiver<ViewCardsViewEvent>,
) {
    BoxWithConstraints {

        val scope = rememberCoroutineScope()

        val swipeableCardState = rememberSwipeableCardState(
            LocalDensity.current.run { maxWidth.toPx() },
            scope
        )

        val flippableCardState = rememberFlippableCardState(scope = scope)

        Column {
            Box(modifier = Modifier.weight(1f))
            {
                nextCard?.let {
                    FlashCard(
                        enabled = false,
                        swipeableCardState = swipeableCardState,
                        text = it.front,
                    )
                }
                currentCard?.let {
                    FlashCard(
                        isCardFlipped = isFlipped,
                        onClickedCard = {
                            eventReceiver.onEvent(ClickedCard)
                        },
                        onSwipedCard = {
                            flippableCardState.resetRotationBySnap()
                            eventReceiver.onEvent(SwipedCard)
                        },
                        swipeableCardState = swipeableCardState,
                        flippableCardState = flippableCardState,
                        text = if (isFlipped) it.back else it.front,
                    )
                }


                SideEffect {
                    if (currentCard === nextCard) {
                        swipeableCardState.resetPositionBySnap()
                        swipeableCardState.resetIsDragging()
                        eventReceiver.onEvent(SwipedCardReset)
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        flippableCardState.resetRotationBySnap()
                        eventReceiver.onEvent(ClickedReturnPreviousCard)
                    },
                    enabled = !swipeableCardState.isDragging
                ) {
                    Icon(
                        modifier = Modifier.alpha(if (swipeableCardState.isDragging) 0.5f else 1f),
                        imageVector = Icons.Default.Undo,
                        tint = MaterialTheme.colors.primary,
                        contentDescription = null
                    )
                }
                IconButton(
                    onClick = {
                        flippableCardState.resetRotationBySnap()
                        eventReceiver.onEvent(ClickedShuffleDeck)
                    },
                    enabled = !swipeableCardState.isDragging
                ) {
                    Row {
                        Icon(
                            modifier = Modifier.alpha(if (swipeableCardState.isDragging) 0.5f else 1f),
                            imageVector = Icons.Default.Shuffle,
                            tint = MaterialTheme.colors.primary,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(if (isShuffled) "ON" else "OFF")
                    }
                }
            }
        }
    }
}