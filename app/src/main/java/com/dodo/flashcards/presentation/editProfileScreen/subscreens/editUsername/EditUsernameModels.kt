package com.dodo.flashcards.presentation.editProfileScreen.subscreens.editUsername

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState

sealed interface EditUsernameViewEvent : ViewEvent {
    object ClickedConfirm : EditUsernameViewEvent
    object ClickedReturn : EditUsernameViewEvent
    data class TextUsernameChanged(val changedTo: String) : EditUsernameViewEvent
}

data class EditUsernameViewState(
    val buttonsEnabled: Boolean = true,
    val errorMessage: String? = null,
    val hasSuccessfullySet: Boolean = false,
    val textUsername: String,
) : ViewState