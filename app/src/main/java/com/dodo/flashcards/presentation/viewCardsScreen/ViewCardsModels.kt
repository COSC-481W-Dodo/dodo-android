package com.dodo.flashcards.presentation.viewCardsScreen

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState
import com.dodo.flashcards.domain.models.Flashcard

sealed interface ViewCardsViewEvent : ViewEvent {
    object ClickedCard : ViewCardsViewEvent
    object ClickedNavigateUp : ViewCardsViewEvent
    object ClickedReturnPreviousCard : ViewCardsViewEvent
    object SwipedCard : ViewCardsViewEvent
    object SwipedCardReset : ViewCardsViewEvent
    object ClickedPreviousReset : ViewCardsViewEvent
}

sealed interface ViewCardsViewState : ViewState {
    data class CardsLoaded(
        val currentCard: Flashcard,
        val dummyCard: Flashcard?,
        val nextCard: Flashcard,
        val isFlipped: Boolean = false,
    ) : ViewCardsViewState
    object CardsLoading : ViewCardsViewState
    object CardsLoadError : ViewCardsViewState
}