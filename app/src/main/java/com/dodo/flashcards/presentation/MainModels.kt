package com.dodo.flashcards.presentation

import com.dodo.flashcards.architecture.Destination
import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState
import com.google.firebase.auth.FirebaseAuth

sealed interface MainViewState : ViewState {
    object Authenticated : MainViewState
    object Unauthenticated : MainViewState
}

sealed interface MainViewEvent : ViewEvent {
}

sealed interface MainDestination : Destination {
    object NavigateLogin : MainDestination
    object NavigateRegister : MainDestination
    object NavigateUp : MainDestination
    object NavigateWelcome : MainDestination
}