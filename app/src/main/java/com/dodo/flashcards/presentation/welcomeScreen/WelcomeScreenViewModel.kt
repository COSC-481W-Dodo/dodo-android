package com.dodo.flashcards.presentation.welcomeScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.usecases.authentication.LogoutUserUseCase
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.MainDestination.*
import com.dodo.flashcards.presentation.welcomeScreen.WelcomeScreenViewEvent.ClickedLogout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WelcomeScreenViewModel @Inject constructor(
    private val logoutUserUseCase: LogoutUserUseCase
) : BaseRoutingViewModel<WelcomeScreenViewState, WelcomeScreenViewEvent, MainDestination>() {

    init {
        pushState(
            WelcomeScreenViewState(
                displayName = "TODO implement display name"
            )
        )
    }

    override fun onEvent(event: WelcomeScreenViewEvent) {
        when (event) {
            is ClickedLogout -> onClickedLogout()
        }
    }

    private fun onClickedLogout() {
        viewModelScope.launch {
            logoutUserUseCase()
            routeTo(NavigateLogin)
        }
    }
}