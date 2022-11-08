package com.dodo.flashcards.presentation.viewCardsScreen

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState
import com.dodo.flashcards.domain.models.Flashcard
import java.util.*


sealed interface ViewCardsViewEvent : ViewEvent {
    object ClickedCard : ViewCardsViewEvent
    object ClickedReturnPreviousCard : ViewCardsViewEvent
    object SwipedCard : ViewCardsViewEvent
    object SwipedCardReset : ViewCardsViewEvent
}

sealed interface ViewCardsViewState : ViewState {
    /**
     * @param hasPreviousCard Informs View whether to show a button which allows
     * user to view the last card.
     * @param nextCardFront Informs View whether to show a card behind the current card
     * and what its content should be.
     */
    data class CardsLoaded(
        val currentCard: Flashcard,
        val nextCard: Flashcard,
        val isFlipped: Boolean = false
    ) : ViewCardsViewState
    object CardsLoading : ViewCardsViewState
    object CardsLoadError : ViewCardsViewState
}