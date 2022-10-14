package com.dodo.flashcards.domain.usecases.authentication

import com.dodo.flashcards.domain.models.AuthRepository
import com.dodo.flashcards.util.Response
import javax.inject.Inject

class LogoutUserUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): Response<Unit> {
        return authRepository.logout()
    }
}