package com.dodo.flashcards.presentation.welcomeScreen

import androidx.lifecycle.SavedStateHandle
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.welcomeScreen.WelcomeScreenViewEvent.ClickedLogout
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class WelcomeScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseRoutingViewModel<WelcomeScreenViewState, WelcomeScreenViewEvent, MainDestination>() {

    override fun onEvent(event: WelcomeScreenViewEvent) {
        when (event) {
            is ClickedLogout -> onClickedLogout()
        }
    }

    private fun onClickedLogout() {

    }
}