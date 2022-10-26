package com.dodo.flashcards.presentation.viewCardsScreen

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState
import com.dodo.flashcards.domain.models.flashcard.Flashcard

sealed interface ViewCardsViewEvent : ViewEvent {
	object SwipedAnyDirection : ViewCardsViewEvent
}

data class ViewCardsViewState(
	val currentIndex: Int,
	val currentCard: Flashcard,
	val nextCard: Flashcard
) : ViewState