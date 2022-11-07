package com.dodo.flashcards.presentation.viewCardsScreen.subscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dodo.flashcards.architecture.EventReceiver
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent.*
import com.dodo.flashcards.presentation.viewCardsScreen.FlippableFlashCard
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent

@Composable
fun CardsLoaded(
    currentCardBack: String,
    currentCardFront: String,
    currentCardIndex: Int,
    currentCardIsFlipped: Boolean,
    nextCardFront: String?,
    eventReceiver: EventReceiver<ViewCardsViewEvent>
) {
    FlippableFlashCard(
        isCardFlipped = currentCardIsFlipped,
        onCardClicked = { eventReceiver.onEvent(ClickedCard) },
        frontContent = currentCardFront,
        backContent = currentCardBack,
        modifier = Modifier.fillMaxSize()
    )
}