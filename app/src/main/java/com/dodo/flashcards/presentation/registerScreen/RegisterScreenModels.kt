package com.dodo.flashcards.presentation.registerScreen

import androidx.compose.ui.graphics.vector.ImageVector
import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState

sealed interface RegisterScreenViewEvent : ViewEvent {
    object ClickedAcknowledgeVerification : RegisterScreenViewEvent
    object ClickedRegister : RegisterScreenViewEvent
    object ClickedShowPassword : RegisterScreenViewEvent
    data class TextEmailChanged(val changedTo: String) : RegisterScreenViewEvent
    data class TextPassChanged(val changedTo: String) : RegisterScreenViewEvent
    data class TextUsernameChanged(val changedTo: String) : RegisterScreenViewEvent
}


sealed interface RegisterScreenViewState : ViewState {
    object Error : RegisterScreenViewState
    data class Idle(
        val errorEmailMessage: String? = null,
        val errorPasswordMessage: String? = null,
        val errorUsernameMessage: String? = null,
        val textEmail: String,
        val textPass: String,
        val textUsername: String,
        val hidePassword: Boolean,
    ) : RegisterScreenViewState

    object Loading : RegisterScreenViewState
    data class Success(val email: String) : RegisterScreenViewState
}