package com.dodo.flashcards.domain.usecases.flashcards

import com.dodo.flashcards.domain.models.FlashcardRepository
import com.dodo.flashcards.domain.models.Tag
import com.dodo.flashcards.domain.usecases.authentication.GetUserIdUseCase
import com.dodo.flashcards.domain.usecases.authentication.GetUserUseCase
import com.dodo.flashcards.util.Response
import javax.inject.Inject

class GetTagsUseCase @Inject constructor(
    private val flashcardRepository: FlashcardRepository,
    private val getUserIdUseCase: GetUserIdUseCase
) {
    suspend operator fun invoke(ownerOnly: Boolean = false): Response<List<Tag>> {
        return try {
            val byAuthor = if (ownerOnly) getUserIdUseCase().data else null
            println("By author is $byAuthor")
            flashcardRepository
                .getTags(byAuthor)
        } catch (e: Exception) {
            Response.Error(exception = e)
        }
    }
}