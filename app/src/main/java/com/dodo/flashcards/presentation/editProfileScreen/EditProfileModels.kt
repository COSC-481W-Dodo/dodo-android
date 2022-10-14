package com.dodo.flashcards.presentation.editProfileScreen

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState

sealed interface EditProfileViewEvent : ViewEvent {
    object ClickedEditUsername : EditProfileViewEvent
    object ClickedEditPassword : EditProfileViewEvent
    object ClickedReturn : EditProfileViewEvent
}

object EditProfileViewState : ViewState