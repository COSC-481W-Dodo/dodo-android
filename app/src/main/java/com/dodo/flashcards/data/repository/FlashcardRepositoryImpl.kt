package com.dodo.flashcards.data.repository

import android.util.Log
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
        private const val FIRESTORE_COLLECTION_FLASHCARDS = "flashcards"
        private const val FIRESTORE_COLLECTION_TAGS = "tags"
        private const val FIRESTORE_FIELD_ANSWER_KEY = "answer"
        private const val FIRESTORE_FIELD_QUESTION_KEY = "question"
        private const val FIRESTORE_FIELD_TAGS_KEY = "tags"
        private const val FIRESTORE_TAG_AUTHOR = "author"
    }

    override suspend fun getFlashcards(tags: List<String>): Response<List<Flashcard>> {
        return try {
            suspendCoroutine { continuation ->
                val flashcardsRef = db.collection(FIRESTORE_COLLECTION_FLASHCARDS)
                flashcardsRef.whereArrayContainsAny(
                        FIRESTORE_FIELD_TAGS_KEY, tags
                    ).get().addOnSuccessListener {
                        continuation.resume(Response.Success(it.documents.map { doc ->
                            object : Flashcard {
                                override val front: String =
                                    doc[FIRESTORE_FIELD_QUESTION_KEY] as String
                                override val back: String =
                                    doc[FIRESTORE_FIELD_ANSWER_KEY] as String
                            }
                        }))
                    }
            }
        } catch (e: Exception) {
            Response.Error(exception = e)
        }
    }

    override suspend fun getTags(): Response<List<Tag>> {
        return try {
            suspendCoroutine { continuation ->
                db.collection(FIRESTORE_COLLECTION_TAGS).get().addOnSuccessListener { docs ->
                    continuation.resume(Response.Success(docs.map {
                        object : Tag {
                            override val value: String = it.id
                        }
                    }))
                }
            }
        } catch (e: Exception) {
            Response.Error(exception = e)
        }
    }

    override suspend fun getUserTags(uid: String): Response<List<Tag>> {
        return try {
            suspendCoroutine { continuation ->
                db.collection(FIRESTORE_COLLECTION_TAGS).whereEqualTo(FIRESTORE_TAG_AUTHOR, uid)
                    .get().addOnSuccessListener { docs ->
                        continuation.resume(Response.Success(docs.map {
                            object : Tag { override val value: String = it.id }
                        }))
                    }
            }
        } catch (e: Exception) {
            Response.Error(exception = e)
        }
    }

    @Deprecated("Delete this later, only used while mocking.")
    private data class TempFlashcard(
        override val front: String, override val back: String
    ) : Flashcard

    @Deprecated("Delete this later, only used while mocking.")
    private data class TempTag(
        override val value: String
    ) : Tag
}