package com.dodo.flashcards.domain.models

import com.dodo.flashcards.util.Response

interface FlashcardRepository {
    suspend fun getFlashcards(tags: List<String>, byAuthor: String?): Response<List<Flashcard>>
    suspend fun getTags(byAuthor: String?): Response<List<Tag>>
}