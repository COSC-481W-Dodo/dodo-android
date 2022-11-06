package com.dodo.flashcards.data.repository

import com.dodo.flashcards.domain.models.Flashcard
import com.dodo.flashcards.domain.models.FlashcardRepository
import com.dodo.flashcards.domain.models.Tag
import com.dodo.flashcards.util.Response
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FlashcardRepositoryImpl @Inject constructor(private val db: FirebaseFirestore) :
    FlashcardRepository {

    companion object {
        private const val FIRESTORE_COLLECTION_TAGS = "tags"
    }

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

    override suspend fun getTags(): Response<List<Tag>> {
        return try {
            suspendCoroutine { continuation ->
                db.collection(FIRESTORE_COLLECTION_TAGS).get().addOnSuccessListener { docs ->
                    continuation.resume(Response.Success(docs.map {
                        (object : Tag {
                            override val value: String = it.id
                        })
                    }))
                }
            }
        } catch (e: Exception) {
            Response.Error(exception = e)
        }
    }

    @Deprecated("Delete this later, only used while mocking.")
    private data class TempFlashcard(
        override val front: String,
        override val back: String
    ) : Flashcard

    @Deprecated("Delete this later, only used while mocking.")
    private data class TempTag(
        override val value: String
    ) : Tag
}