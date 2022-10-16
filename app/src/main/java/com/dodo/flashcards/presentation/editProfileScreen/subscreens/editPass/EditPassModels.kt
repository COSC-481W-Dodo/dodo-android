package com.dodo.flashcards.presentation.editProfileScreen.subscreens.editPass

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState

sealed interface EditPassViewEvent : ViewEvent {
    object ClickedConfirm : EditPassViewEvent
    object ClickedReturn : EditPassViewEvent
    object ClickedShowPasswordOld : EditPassViewEvent
    object ClickedShowPasswordNew : EditPassViewEvent
    data class TextPassNewChanged(val changedTo: String) : EditPassViewEvent
    data class TextPassOldChanged(val changedTo: String) : EditPassViewEvent
}

data class EditPassViewState(
    val confirmButtonEnabled: Boolean,
    val hasSuccessfullySet: Boolean,
    val passHiddenOld: Boolean,
    val passHiddenNew: Boolean,
    val textPassNew: String,
    val textPassOld: String
) : ViewState