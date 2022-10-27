package com.dodo.flashcards.presentation.viewCardsScreen

import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.models.flashcard.FlashcardImpl
import com.dodo.flashcards.domain.models.flashcard.Flashcard
import com.dodo.flashcards.presentation.MainDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent.SwipedAnyDirection
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent.Flip

@HiltViewModel
class ViewCardsViewModel @Inject constructor(
) : BaseRoutingViewModel<ViewCardsViewState, ViewCardsViewEvent, MainDestination>() {

	//placeholder list construction
	val cards: List<Flashcard> = listOf<Flashcard>(
		FlashcardImpl("This is my front", "This is my back"),
		FlashcardImpl("This is my other front", "This is my other back")
	)

	init {
		pushState(
			ViewCardsViewState(
				currentIndex = 0,
				currentCard = cards[0],
				nextCard = cards[1]
			)
		)
	}

	override fun onEvent(event: ViewCardsViewEvent) {
		when (event) {
			is SwipedAnyDirection -> onSwiped()
			is Flip -> onFlip()
		}
	}

	private fun onSwiped() {
		TODO("Not yet implemented")
	}

	private fun onFlip() {
		TODO("Not yet implemented")
	}


}