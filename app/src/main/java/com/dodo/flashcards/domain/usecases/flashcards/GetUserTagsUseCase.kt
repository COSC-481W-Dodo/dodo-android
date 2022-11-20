package com.dodo.flashcards.domain.usecases.flashcards

import com.dodo.flashcards.domain.models.FlashcardRepository
import com.dodo.flashcards.domain.models.Tag
import com.dodo.flashcards.util.Response
import javax.inject.Inject

class GetUserTagsUseCase @Inject constructor(
    private val flashcardRepository: FlashcardRepository
) {
    suspend operator fun invoke(uid: String): Response<List<Tag>> {
        return flashcardRepository.getUserTags(uid)
    }
}