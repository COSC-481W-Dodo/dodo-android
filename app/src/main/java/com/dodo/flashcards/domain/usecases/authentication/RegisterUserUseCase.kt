package com.dodo.flashcards.domain.usecases.authentication

import com.dodo.flashcards.domain.models.AuthRepository
import com.dodo.flashcards.domain.models.User
import com.dodo.flashcards.util.Response
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(
        email: String,
        password: String,
        username: String
    ): Response<User> {
        return authRepository.registerUserWithUsername(email, password, username)
    }
}