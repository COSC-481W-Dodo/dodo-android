package com.dodo.flashcards.domain.models

import com.dodo.flashcards.util.Resource
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun getCurrentUser(): FirebaseUser?

    suspend fun registerUserWithUsername(
        email: String,
        password: String,
        username: String
    ): Resource<FirebaseUser>

    suspend fun login(
        email: String,
        password: String
    ): Resource<FirebaseUser>

    suspend fun logout()

    suspend fun sendPasswordResetEmail(email: String): Boolean
}