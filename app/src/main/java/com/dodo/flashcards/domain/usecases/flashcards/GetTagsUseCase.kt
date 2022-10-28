package com.dodo.flashcards.domain.usecases.flashcards

import com.dodo.flashcards.domain.models.FlashcardRepository
import com.dodo.flashcards.domain.models.Tag
import com.dodo.flashcards.util.Response
import javax.inject.Inject

class GetTagsUseCase @Inject constructor(
    private val flashcardRepository: FlashcardRepository
) {
    suspend operator fun invoke(): Response<List<Tag>> {
        return flashcardRepository.getTags()
    }
}