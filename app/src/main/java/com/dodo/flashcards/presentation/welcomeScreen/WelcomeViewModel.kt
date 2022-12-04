package com.dodo.flashcards.presentation.welcomeScreen

import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.usecases.authentication.GetUserUseCase
import com.dodo.flashcards.domain.usecases.authentication.LogoutUserUseCase
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.MainDestination.*
import com.dodo.flashcards.presentation.welcomeScreen.WelcomeScreenViewEvent.*
import com.dodo.flashcards.util.doOnError
import com.dodo.flashcards.util.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val logoutUserUseCase: LogoutUserUseCase,
) : BaseRoutingViewModel<WelcomeScreenViewState, WelcomeScreenViewEvent, MainDestination>() {

    init {
        viewModelScope.launch {
            getUserUseCase()
                .doOnSuccess {
                    WelcomeScreenViewState(
                        username = data.username,
                        isMenuOpen = false
                    ).push()
                }
                .doOnError {
                    // Todo, handle failed to retrieve authenticated user
                }
        }
    }

    override fun onEvent(event: WelcomeScreenViewEvent) {
        when (event) {
            is ClickedEditProfile -> onClickedEditProfile()
            is ClickedLogout -> onClickedLogout()
            is ClickedViewTags -> onClickedViewTags()
            is ClickedMenu -> onClickedMenu()
        }
    }

    private fun onClickedMenu() {
        lastPushedState?.run {
            copy(
                isMenuOpen = !isMenuOpen
            )
        }?.push()
    }

    private fun onClickedEditProfile() {
        routeTo(NavigateEditProfile)
    }

    private fun onClickedLogout() {
        viewModelScope.launch {
            logoutUserUseCase()
            routeTo(NavigateLogin)
        }
    }

    private fun onClickedViewTags() {
        routeTo(NavigateViewTags)
    }
}