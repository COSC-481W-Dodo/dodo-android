package com.dodo.flashcards.data.repository

import com.dodo.flashcards.domain.models.Flashcard
import com.dodo.flashcards.domain.models.FlashcardRepository
import com.dodo.flashcards.util.Response

class FlashcardRepositoryImpl : FlashcardRepository {
    override suspend fun getFlashcards(tags: List<String>): Response<List<Flashcard>> {
        // TODO, this is mocked, do something later
        return Response.Success(
            listOf(
                TempFlashcard(
                    front = "This is my front",
                    back = "This is my back"
                ),
                TempFlashcard(
                    front = "This is my front 1",
                    back = "This is my back 1"
                ),
            )
        )
    }

    @Deprecated("Delete this later, only used while mocking.")
    private data class TempFlashcard(
        override val front: String,
        override val back: String
    ) : Flashcard
}