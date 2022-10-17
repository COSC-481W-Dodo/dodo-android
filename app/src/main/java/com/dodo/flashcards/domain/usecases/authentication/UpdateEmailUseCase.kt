package com.dodo.flashcards.domain.usecases.authentication

import com.dodo.flashcards.domain.models.AuthRepository
import com.dodo.flashcards.util.Response
import javax.inject.Inject

class UpdateEmailUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(
        newEmail: String,
        password: String
    ): Response<Unit> {
        return authRepository.updateEmail(newEmail, password)
    }
}