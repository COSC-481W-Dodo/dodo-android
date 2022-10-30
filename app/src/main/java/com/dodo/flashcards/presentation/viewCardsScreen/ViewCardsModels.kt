package com.dodo.flashcards.presentation.viewCardsScreen

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState
import com.dodo.flashcards.domain.models.Flashcard


sealed interface ViewCardsViewEvent : ViewEvent {
	object SwipedAnyDirection : ViewCardsViewEvent
	object CardClicked : ViewCardsViewEvent
}

data class ViewCardsViewState(
	val currentIndex: Int,
	val currentCard: Flashcard,
	val nextCard: Flashcard,
	val isFlipped: Boolean
) : ViewState