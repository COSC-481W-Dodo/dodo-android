package com.dodo.flashcards.di

import com.dodo.flashcards.data.repository.AuthRepositoryImpl
import com.dodo.flashcards.data.repository.FlashcardRepositoryImpl
import com.dodo.flashcards.domain.models.AuthRepository
import com.dodo.flashcards.domain.models.FlashcardRepository
import com.dodo.flashcards.util.UserUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth
    ): AuthRepository = AuthRepositoryImpl(firebaseAuth)

    @Provides
    fun provideFlashcardRepository(
        db: FirebaseFirestore
    ): FlashcardRepository = FlashcardRepositoryImpl(db)

    @Provides
    fun provideUserUtils(): UserUtils = UserUtils()

    @Singleton
    @Provides
    fun provideDb(): FirebaseFirestore = Firebase.firestore
}