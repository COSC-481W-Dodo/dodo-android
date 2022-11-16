package com.dodo.flashcards.presentation.viewCardsScreen.subscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alexstyl.swipeablecard.ExperimentalSwipeableCardApi
import com.dodo.flashcards.architecture.EventReceiver
import com.dodo.flashcards.domain.models.Flashcard
import com.dodo.flashcards.presentation.common.commonModifiers.*
import com.dodo.flashcards.presentation.viewCardsScreen.DummyCard
import com.dodo.flashcards.presentation.viewCardsScreen.FlippableFlashCard
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardsLoaded(
    currentCard: Flashcard?,
    nextCard: Flashcard?,
    isFlipped: Boolean,
    eventReceiver: EventReceiver<ViewCardsViewEvent>,
) {

    val cardBackgroundColor = MaterialTheme.colors.primaryVariant
    val cardTextColor = MaterialTheme.colors.onBackground
    val scope = rememberCoroutineScope()

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val swipeState = rememberSwipeState(
            maxWidth = constraints.maxWidth.toFloat(),
            maxHeight = constraints.maxHeight.toFloat()
        )

/*
        val flipState = rememberFlipState(isFlipped)
*/
        val flipState = FlipState()
        SideEffect {
            if (currentCard == nextCard) {
                swipeState.snapBack(scope)
                eventReceiver.onEvent(SwipedCardReset)
            }
        }
        nextCard?.let {
            DummyCard(
                modifier = Modifier
                    .fillMaxSize(0.88f)
                    .align(Alignment.Center),
                frontContent = it.front,
                backgroundColor = cardBackgroundColor,
                textColor = cardTextColor
            )
        }
        currentCard?.let {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .flip(
                        state = flipState,
                        onClick = { /*eventReceiver.onEvent(ClickedCard)*/ },
                        transitionContent = { eventReceiver.onEvent(ClickedCard)}

                    )
                    .swipe(
                        state = swipeState,
                        onDragAccepted = { eventReceiver.onEvent(SwipedCard) }
                    )
            ) {
                FlippableFlashCard(
                    modifier = Modifier
                        .fillMaxSize(.88f)
                        .align(Alignment.Center),
                    isCardFlipped = false,
                    onCardClicked = { /*eventReceiver.onEvent(ClickedCard)*/ },
                    onClickedPrevious = { eventReceiver.onEvent(ClickedReturnPreviousCard) },
                    frontContent = it.front,
                    backContent = it.back,
                    flipDurationMillis = 200,
                    backgroundColor = cardBackgroundColor,
                    textColor = cardTextColor
                )
            }
        }
    }
}




