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
    val clickEventsEnabled: Boolean = true,
    val hasSuccessfullySet: Boolean = false,
    val errorMessagePassNew: String? = null,
    val errorMessagePassOld: String? = null,
    val passHiddenNew: Boolean = false,
    val passHiddenOld: Boolean = false,
    val textPassNew: String = String(),
    val textPassOld: String = String()
) : ViewState