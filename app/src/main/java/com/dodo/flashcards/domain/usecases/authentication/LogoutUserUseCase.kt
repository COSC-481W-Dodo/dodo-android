package com.dodo.flashcards.domain.usecases.authentication

import com.dodo.flashcards.domain.models.AuthRepository
import javax.inject.Inject


class LogoutUserUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke() {
        authRepository.logout()
    }
}