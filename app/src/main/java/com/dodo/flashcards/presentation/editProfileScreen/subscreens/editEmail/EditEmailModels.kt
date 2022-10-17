package com.dodo.flashcards.presentation.editProfileScreen.subscreens.editEmail

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState
import com.dodo.flashcards.presentation.editProfileScreen.subscreens.editPass.EditPassViewEvent

sealed interface EditEmailViewEvent : ViewEvent {
    object ClickedConfirm : EditEmailViewEvent
    object ClickedReturn : EditEmailViewEvent
    object ClickedShowPassword : EditEmailViewEvent
    data class TextPassChanged(val changedTo: String) : EditEmailViewEvent
    data class TextEmailChanged(val changedTo: String) : EditEmailViewEvent
}

data class EditEmailViewState(
    val confirmButtonEnabled: Boolean = false,
    val errorEmailMessage: String? = null,
    val errorPassMessage: String? = null,
    val hasSuccessfullySet: Boolean = false,
    val passHidden: Boolean = false,
    val textPass: String = String(),
    val textEmail: String = String()
) : ViewState

