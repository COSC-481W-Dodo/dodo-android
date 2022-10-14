package com.dodo.flashcards.domain.usecases.authentication

import com.dodo.flashcards.domain.models.AuthRepository
import com.dodo.flashcards.domain.models.User
import com.dodo.flashcards.util.Response
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): Response<User> {
        return authRepository.getCurrentUser()
    }
}