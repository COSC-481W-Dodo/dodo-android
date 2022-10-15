package com.dodo.flashcards.data.repository

import com.dodo.flashcards.data.response.UserResponse
import com.dodo.flashcards.domain.models.AuthRepository
import com.dodo.flashcards.domain.models.User
import com.dodo.flashcards.util.Response
import com.dodo.flashcards.util.Response.Error
import com.dodo.flashcards.util.Response.Success
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val auth: FirebaseAuth) : AuthRepository {

    override suspend fun getCurrentUser(): Response<User> {
        return try {
            Success(auth.currentUser!!.run {
                UserResponse(
                    email = email ?: "Error: Missing email",
                    username = displayName ?: "Error: Missing username",
                )
            })
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Error(exception = e)
        }
    }

    override suspend fun registerUserWithUsername(
        email: String,
        password: String,
        username: String
    ): Response<User> {
        return try {

            auth.createUserWithEmailAndPassword(email, password).await().user!!.let { newUser ->
                newUser.updateProfile(
                    UserProfileChangeRequest.Builder().setDisplayName(username).build()
                ).await()
                Success(
                    UserResponse(
                        email = email,
                        username = username
                    )
                )
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Error(exception = e)
        }
    }

    override suspend fun login(email: String, password: String): Response<User> {
        return try {
            Success(auth.signInWithEmailAndPassword(email, password).await().user!!.run {
                UserResponse(
                    email = email,
                    username = displayName ?: "Error: Missing username",
                )
            })
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Error(exception = e)
        }
    }

    override suspend fun logout(): Response<Unit> {
        return try {
            auth.signOut()
            Success(Unit)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Error(exception = e)
        }
    }

    override suspend fun sendPasswordResetEmail(email: String): Response<Unit> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            Success(Unit)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Error(exception = e)
        }
    }

    override suspend fun updatePassword(
        oldPassword: String,
        newPassword: String
    ): Response<Unit> {
        return try {
            auth.currentUser!!.run {
                val email = email!!
                reauthenticate(auth.run {
                    EmailAuthProvider.getCredential(email, oldPassword)
                }).await()
                updatePassword(newPassword).await()
                Success(Unit)
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            println("here error was $e")
            Error(exception = e)
        }
    }

    override suspend fun updateUsername(username: String): Response<Unit> {
        return try {
            auth.currentUser!!.run {
                updateProfile(
                    UserProfileChangeRequest
                        .Builder()
                        .setDisplayName(username)
                        .build()
                )
                Success(Unit)
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Error(exception = e)
        }
    }
}