package com.dodo.flashcards.presentation.registerScreen

import androidx.compose.ui.graphics.vector.ImageVector
import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState

sealed interface RegisterScreenViewEvent : ViewEvent {
    object ClickedRegister : RegisterScreenViewEvent
    data class TextEmailChanged(val changedTo: String) : RegisterScreenViewEvent
    data class TextPassChanged(val changedTo: String) : RegisterScreenViewEvent
    data class TextUsernameChanged(val changedTo: String) : RegisterScreenViewEvent
    object ClickedShowPassword : RegisterScreenViewEvent
}

data class RegisterScreenViewState(
    val buttonsEnabled: Boolean,
    val textEmail: String,
    val textPass: String,
    val textUsername: String,
    val hidePassword: Boolean,
) : ViewState