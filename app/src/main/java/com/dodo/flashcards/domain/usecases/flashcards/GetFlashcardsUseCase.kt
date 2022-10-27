package com.dodo.flashcards.domain.usecases.flashcards

import com.dodo.flashcards.domain.models.Flashcard
import com.dodo.flashcards.domain.models.FlashcardRepository
import com.dodo.flashcards.util.Response
import javax.inject.Inject

class GetFlashcardsUseCase @Inject constructor(
    private val flashcardRepository: FlashcardRepository
) {
    suspend operator fun invoke(tags: List<String>): Response<List<Flashcard>> {
        return flashcardRepository.getFlashcards(tags)
    }
}