package com.dodo.flashcards.domain.models

import com.dodo.flashcards.util.Response

interface AuthRepository {
    suspend fun getCurrentUser(): Response<User>

    suspend fun registerUserWithUsername(
        email: String,
        password: String,
        username: String
    ): Response<User>

    suspend fun login(
        email: String,
        password: String
    ): Response<User>

    suspend fun logout(): Response<Unit>
    suspend fun sendPasswordResetEmail(email: String): Response<Unit>
    suspend fun updatePassword(oldPassword: String, newPassword: String): Response<Unit>
    suspend fun updateUsername(username: String): Response<Unit>
}