package com.dodo.flashcards.presentation.register_screen

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState

sealed interface RegisterScreenViewEvent : ViewEvent {
    object ClickedRegister : RegisterScreenViewEvent
    data class TextEmailChanged(val changedTo: String) : RegisterScreenViewEvent
    data class TextPassChanged(val changedTo: String) : RegisterScreenViewEvent
    // Todo, add remaining text change events when we finalize what info we will collect
}

data class RegisterScreenViewState(
    val textEmail: String,
    val textPass: String
) : ViewState