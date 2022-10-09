package com.dodo.flashcards.presentation

import com.dodo.flashcards.architecture.Destination
import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState
import com.google.firebase.auth.FirebaseAuth

sealed interface MainViewState : ViewState {
    object Unauthenticated : MainViewState
    object Authenticated : MainViewState

}

sealed interface MainViewEvent : ViewEvent {
    data class AuthenticateUser(val auth : FirebaseAuth) : MainViewEvent
}

sealed interface MainDestination : Destination {
    object NavigateLogin : MainDestination
    object NavigateUp : MainDestination

}