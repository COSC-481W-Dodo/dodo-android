package com.dodo.flashcards.domain.usecases.authentication

import com.dodo.flashcards.domain.models.AuthRepository
import com.dodo.flashcards.util.Response
import javax.inject.Inject

class ForgotPassSendEmailUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String): Response<Unit> {
        return authRepository.sendPasswordResetEmail(email)
    }
}