package com.dodo.flashcards.presentation.login_screen

import androidx.lifecycle.SavedStateHandle
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.login_screen.LoginScreenViewEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseRoutingViewModel<LoginScreenViewState, LoginScreenViewEvent, MainDestination>() {

    override fun onEvent(event: LoginScreenViewEvent) {
        when (event) {
            is ClickedForgotPassword -> onClickedForgotPassword()
            is ClickedLogin -> onClickedLogin()
            is ClickedRegister -> onClickedRegister()
            is TextEmailChanged -> onTextEmailChanged(event)
            is TextPassChanged -> onTextPassChanged(event)
        }
    }

    private fun onClickedForgotPassword() {

    }

    private fun onClickedLogin() {

    }

    private fun onClickedRegister() {

    }

    private fun onTextEmailChanged(event: TextEmailChanged) {

    }

    private fun onTextPassChanged(event: TextPassChanged) {

    }
}