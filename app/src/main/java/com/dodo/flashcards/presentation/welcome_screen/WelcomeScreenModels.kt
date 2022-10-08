package com.dodo.flashcards.presentation.welcome_screen

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState

sealed interface WelcomeScreenViewEvent : ViewEvent {
    object ClickedLogout : WelcomeScreenViewEvent
}

data class WelcomeScreenViewState(
    val displayName: String
) : ViewState