package com.dodo.flashcards.data.repository

import com.dodo.flashcards.domain.models.AuthRepository
import com.dodo.flashcards.util.Resource
import com.dodo.flashcards.util.Resource.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val auth: FirebaseAuth) : AuthRepository {

    override suspend fun getCurrentUser(): FirebaseUser? = auth.currentUser

    override suspend fun registerUserWithUsername(
        email: String,
        password: String,
        username: String
    ): Resource<FirebaseUser> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await().user!!.let { newUser ->
                newUser.updateProfile(
                    UserProfileChangeRequest.Builder().setDisplayName(username).build()
                ).await()
                Success(newUser)
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Error(exception = e)
        }
    }

    override suspend fun login(email: String, password: String): Resource<FirebaseUser> {
        return try {
            val response = auth.signInWithEmailAndPassword(email, password).await().user!!
            Success(response)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Error(exception = e)
        }
    }

    override suspend fun logout() {
        auth.signOut()
    }

    override suspend fun sendPasswordResetEmail(email: String): Boolean {
        return try {
            auth.sendPasswordResetEmail(email).await()
            true
        } catch (e: Exception) {
            false
        }
    }
}