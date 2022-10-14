package com.dodo.flashcards.presentation.registerScreen

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState

sealed interface RegisterScreenViewEvent : ViewEvent {
    object ClickedRegister : RegisterScreenViewEvent
    data class TextEmailChanged(val changedTo: String) : RegisterScreenViewEvent
    data class TextPassChanged(val changedTo: String) : RegisterScreenViewEvent
    data class TextUsernameChanged(val changedTo: String) : RegisterScreenViewEvent
}

data class RegisterScreenViewState(
    val buttonsEnabled: Boolean,
    val textEmail: String,
    val textPass: String,
    val textUsername: String
) : ViewState