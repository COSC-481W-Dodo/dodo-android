package com.dodo.flashcards.domain.models.flashcard

data class FlashcardImpl(
	override val front: String,
	override val back: String,
) : Flashcard