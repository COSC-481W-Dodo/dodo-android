package com.dodo.flashcards.presentation.editProfileScreen.subscreens.editPass

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState

sealed interface EditPassViewEvent : ViewEvent {
    object ClickedConfirm : EditPassViewEvent
    object ClickedReturn : EditPassViewEvent
    data class TextPassChanged(val changedTo: String) : EditPassViewEvent
}

data class EditPassViewState(val textPass: String) : ViewState