package com.dodo.flashcards.presentation.viewCardsScreen

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState
import com.dodo.flashcards.domain.models.Flashcard


sealed interface ViewCardsViewEvent : ViewEvent {
    object SwipedAnyDirection : ViewCardsViewEvent
    object ClickedCard : ViewCardsViewEvent
}

sealed interface ViewCardsViewState : ViewState {
    data class CardsLoaded(
        val currentCardBack: String,
        val currentCardFront: String,
        val currentCardIndex: Int,
        val currentCardIsFlipped: Boolean,
        val nextCardFront: String?
    ) : ViewCardsViewState
    object CardsLoading : ViewCardsViewState
    object CardsLoadError : ViewCardsViewState
}