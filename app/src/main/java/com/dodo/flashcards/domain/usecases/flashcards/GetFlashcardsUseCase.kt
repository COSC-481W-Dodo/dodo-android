package com.dodo.flashcards.domain.usecases.flashcards

import com.dodo.flashcards.domain.models.Flashcard
import com.dodo.flashcards.domain.models.FlashcardRepository
import com.dodo.flashcards.domain.usecases.authentication.GetUserIdUseCase
import com.dodo.flashcards.util.Response
import javax.inject.Inject

class GetFlashcardsUseCase @Inject constructor(
    private val flashcardRepository: FlashcardRepository,
    private val getUserIdUseCase: GetUserIdUseCase
) {
    suspend operator fun invoke(tags: List<String>, ownerOnly: Boolean): Response<List<Flashcard>> {
        return try {
            val byAuthor = if (ownerOnly) getUserIdUseCase().data else null
            println("By author is $byAuthor")
            flashcardRepository
                .getFlashcards(tags, byAuthor)
        } catch (e: Exception) {
            Response.Error(exception = e)
        }
    }
}