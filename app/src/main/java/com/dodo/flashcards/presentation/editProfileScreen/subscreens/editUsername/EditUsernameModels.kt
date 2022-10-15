package com.dodo.flashcards.presentation.editProfileScreen.subscreens.editUsername

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState

sealed interface EditUsernameViewEvent : ViewEvent {
    object ClickedConfirm : EditUsernameViewEvent
    object ClickedReturn : EditUsernameViewEvent
    data class TextUsernameChanged(val changedTo: String) : EditUsernameViewEvent
}

data class EditUsernameViewState(
    val confirmButtonEnabled: Boolean,
    val hasSuccessfullySet: Boolean,
    val textUsername: String,
) : ViewState