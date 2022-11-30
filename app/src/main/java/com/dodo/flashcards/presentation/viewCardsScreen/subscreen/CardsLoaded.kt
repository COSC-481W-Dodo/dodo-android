package com.dodo.flashcards.presentation.viewCardsScreen.subscreen

import android.util.Log
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
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
    eventReceiver: EventReceiver<ViewCardsViewEvent>,
) {
    val scope = rememberCoroutineScope()
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val swipeableCardState = rememberSwipeableCardState(
            LocalDensity.current.run { maxWidth.toPx() }, scope
        )

        val flippableCardState = rememberFlippableCardState(scope = scope)
        nextCard?.let {
            FlashCard(
                enabled = false,
                swipeableCardState = swipeableCardState,
                text = it.front,
                //   isDummy = false,
            )
        }
        currentCard?.let {
            FlashCard(
                isCardFlipped = isFlipped,
                onClickedCard = {
                    eventReceiver.onEvent(ClickedCard)
                },
                onClickedPrevious = {
                    flippableCardState.resetRotationBySnap()
                    eventReceiver.onEvent(ClickedReturnPreviousCard)
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
                swipeableCardState.resetIsDragging();
                eventReceiver.onEvent(SwipedCardReset)
            }
        }
    }
}