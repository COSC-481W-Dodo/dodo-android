package com.dodo.flashcards.domain.models

import com.dodo.flashcards.util.Response

interface FlashcardRepository {
    suspend fun getFlashcards(tags: List<String>): Response<List<Flashcard>>
}