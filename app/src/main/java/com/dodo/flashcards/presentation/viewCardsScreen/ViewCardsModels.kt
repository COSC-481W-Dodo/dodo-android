package com.dodo.flashcards.presentation.viewCardsScreen

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState
import com.dodo.flashcards.domain.models.Flashcard

sealed interface ViewCardsViewEvent : ViewEvent {
    object ClickedCard : ViewCardsViewEvent
    object ClickedNavigateUp : ViewCardsViewEvent
    object ClickedReturnPreviousCard : ViewCardsViewEvent
    object ClickedShuffleDeck : ViewCardsViewEvent
    object SwipedCard : ViewCardsViewEvent
    object SwipedCardReset : ViewCardsViewEvent
}

sealed interface ViewCardsViewState : ViewState {
    data class CardsLoaded(
        val currentCard: Flashcard,
        val nextCard: Flashcard,
        val isFlipped: Boolean = false,
        val isShuffled: Boolean = false,
    ) : ViewCardsViewState

    object CardsLoading : ViewCardsViewState
    object CardsLoadError : ViewCardsViewState
}