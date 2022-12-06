package com.dodo.flashcards.presentation

import com.dodo.flashcards.architecture.Destination
import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState

sealed interface MainViewState : ViewState {
    object Authenticated : MainViewState
    object Unauthenticated : MainViewState
}

sealed interface MainViewEvent : ViewEvent {
}

sealed interface MainDestination : Destination {
    object NavigateEditEmail : MainDestination
    object NavigateEditPass : MainDestination
    object NavigateEditProfile : MainDestination
    object NavigateEditUsername : MainDestination
    object NavigateForgotPass : MainDestination
    object NavigateLogin : MainDestination
    object NavigateRegister : MainDestination
    object NavigateUp : MainDestination
    object NavigateWelcome : MainDestination
    data class NavigateViewCards(val tags: List<String>, val ownerOnly: Boolean) : MainDestination
    data class NavigateViewTags(val ownerOnly: Boolean) : MainDestination
}