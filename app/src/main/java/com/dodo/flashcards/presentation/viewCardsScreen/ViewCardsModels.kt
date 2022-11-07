package com.dodo.flashcards.presentation.viewCardsScreen

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState
import com.dodo.flashcards.domain.models.Flashcard


sealed interface ViewCardsViewEvent : ViewEvent {
    object ClickedCard : ViewCardsViewEvent
    object ClickedReturnPreviousCard : ViewCardsViewEvent
    object SwipedCard : ViewCardsViewEvent
}

sealed interface ViewCardsViewState : ViewState {
    /**
     * @param hasPreviousCard Informs View whether to show a button which allows
     * user to view the last card.
     * @param nextCardFront Informs View whether to show a card behind the current card
     * and what its content should be.
     */
    data class CardsLoaded(
        val currentCardBack: String,
        val currentCardFront: String,
        val currentCardIsFlipped: Boolean,
        val hasPreviousCard: Boolean,
        val nextCardFront: String?
    ) : ViewCardsViewState
    object CardsLoading : ViewCardsViewState
    object CardsLoadError : ViewCardsViewState
}