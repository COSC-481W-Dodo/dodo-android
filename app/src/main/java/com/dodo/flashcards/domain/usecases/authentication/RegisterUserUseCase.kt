package com.dodo.flashcards.domain.usecases.authentication

import com.dodo.flashcards.domain.models.AuthRepository
import com.dodo.flashcards.util.Resource
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Resource<FirebaseUser> {
        return authRepository.registerUser(email, password)
    }
}