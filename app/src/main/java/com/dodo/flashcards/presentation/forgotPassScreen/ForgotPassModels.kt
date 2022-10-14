package com.dodo.flashcards.presentation.forgotPassScreen

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState

sealed interface ForgotPassViewEvent : ViewEvent {
    object ClickedConfirmEmail : ForgotPassViewEvent
    object ClickedReturn : ForgotPassViewEvent
    data class TextChangedEmail(val changedTo: String) : ForgotPassViewEvent
}

sealed interface ForgotPassViewState : ViewState {
    val textEmail: String

    data class InputEmail(override val textEmail: String) : ForgotPassViewState
    data class InvalidEmail(override val textEmail: String) : ForgotPassViewState
    data class PendingConfirmation(override val textEmail: String) : ForgotPassViewState
}