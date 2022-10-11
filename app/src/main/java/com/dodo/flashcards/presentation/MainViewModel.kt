package com.dodo.flashcards.presentation

import com.dodo.flashcards.architecture.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.dodo.flashcards.presentation.MainViewState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    firebaseAuth: FirebaseAuth
) : BaseViewModel<MainViewState, MainViewEvent>() {

    init {
        firebaseAuth.currentUser.run {
            if (this == null) Unauthenticated else Authenticated
        }.push()
    }

    override fun onEvent(event: MainViewEvent) {
    }
}