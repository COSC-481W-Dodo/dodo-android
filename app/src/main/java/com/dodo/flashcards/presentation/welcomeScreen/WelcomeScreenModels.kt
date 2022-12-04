package com.dodo.flashcards.presentation.welcomeScreen

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState

sealed interface WelcomeScreenViewEvent : ViewEvent {
    object ClickedEditProfile : WelcomeScreenViewEvent
    object ClickedLogout : WelcomeScreenViewEvent
    object ClickedViewTags : WelcomeScreenViewEvent
    object ClickedMenu : WelcomeScreenViewEvent
}
data class WelcomeScreenViewState(
    val username: String?,
    val isMenuOpen: Boolean,
) : ViewState