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

    data class InputEmail(
        val isError: Boolean,
        override val textEmail: String
    ) : ForgotPassViewState

    data class Loading(override val textEmail: String) : ForgotPassViewState
    data class PendingConfirmation(override val textEmail: String) : ForgotPassViewState
}