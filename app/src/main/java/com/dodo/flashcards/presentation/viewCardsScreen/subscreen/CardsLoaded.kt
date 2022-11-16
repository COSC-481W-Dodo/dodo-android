package com.dodo.flashcards.presentation.viewCardsScreen.subscreen

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import com.dodo.flashcards.architecture.EventReceiver
import com.dodo.flashcards.domain.models.Flashcard
import com.dodo.flashcards.presentation.viewCardsScreen.FlashCard
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent.*
import com.dodo.flashcards.presentation.viewCardsScreen.modifiers.rememberSwipeableCardState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardsLoaded(
    currentCard: Flashcard?,
    nextCard: Flashcard?,
    isFlipped: Boolean,
    eventReceiver: EventReceiver<ViewCardsViewEvent>,
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val swipeableCardState = rememberSwipeableCardState(
            LocalDensity.current.run { maxWidth.toPx() },
            rememberCoroutineScope()
        )
        nextCard?.let {
            FlashCard(
                enabled = false,
                swipeableCardState = swipeableCardState,
                text = it.front
            )
        }
        currentCard?.let {
            FlashCard(
                isCardFlipped = isFlipped,
                onClickedCard = {
                    eventReceiver.onEvent(ClickedCard)
                },
                onClickedPrevious = {
                    eventReceiver.onEvent(ClickedReturnPreviousCard)
                },
                onSwipedCard = {
                    eventReceiver.onEvent(SwipedCard)
                },
                swipeableCardState = swipeableCardState,
                text = if (isFlipped) it.back else it.front
            )
        }
        SideEffect {
            if (currentCard === nextCard) {
                swipeableCardState.resetPositionBySnap()
                eventReceiver.onEvent(SwipedCardReset)
            }
        }
    }
}




