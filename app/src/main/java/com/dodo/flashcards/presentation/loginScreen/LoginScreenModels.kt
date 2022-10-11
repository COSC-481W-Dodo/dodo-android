package com.dodo.flashcards.presentation.loginScreen

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState

sealed interface LoginScreenViewEvent : ViewEvent {
    object ClickedForgotPassword : LoginScreenViewEvent
    object ClickedLogin : LoginScreenViewEvent
    object ClickedRegister : LoginScreenViewEvent
    data class TextEmailChanged(val changedTo: String) : LoginScreenViewEvent
    data class TextPassChanged(val changedTo: String) : LoginScreenViewEvent
}

data class LoginScreenViewState(
    val textEmail: String,
    val textPass: String
) : ViewState