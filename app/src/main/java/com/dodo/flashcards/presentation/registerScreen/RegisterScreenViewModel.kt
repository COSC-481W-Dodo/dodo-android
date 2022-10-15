package com.dodo.flashcards.presentation.registerScreen

import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.usecases.authentication.RegisterUserUseCase
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.MainDestination.*
import com.dodo.flashcards.presentation.registerScreen.RegisterScreenViewEvent.*
import com.dodo.flashcards.util.doOnError
import com.dodo.flashcards.util.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : BaseRoutingViewModel<RegisterScreenViewState, RegisterScreenViewEvent, MainDestination>() {

    init {
        pushState(
            RegisterScreenViewState(
                buttonsEnabled = true,
                hidePassword = false,
                textEmail = String(),
                textPass = String(),
                textUsername = String()
            )
        )
    }

    override fun onEvent(event: RegisterScreenViewEvent) {
        when (event) {
            is ClickedRegister -> onClickedRegister()
            is TextEmailChanged -> onTextEmailChanged(event)
            is TextPassChanged -> onTextPassChanged(event)
            is TextUsernameChanged -> onTextUsernameChanged(event)
            is ClickedShowPassword -> onShowPasswordClicked()
        }
    }

    private fun onClickedRegister() {
        withLastState {
            copy(buttonsEnabled = false).push()
            viewModelScope.launch(Dispatchers.IO) {
                registerUserUseCase(textEmail, textPass, textUsername)
                    .doOnSuccess {
                        withContext(Dispatchers.Main) {
                            routeTo(NavigateWelcome)
                        }
                    }
                    .doOnError {
                        copy(buttonsEnabled = true).push()
                        // Todo: Send error ViewState
                    }
            }
        }
    }

    private fun onTextEmailChanged(event: TextEmailChanged) {
        lastPushedState?.copy(textEmail = event.changedTo)?.push()
    }

    private fun onTextPassChanged(event: TextPassChanged) {
        lastPushedState?.copy(textPass = event.changedTo)?.push()
    }

    private fun onTextUsernameChanged(event: TextUsernameChanged) {
        lastPushedState?.copy(textUsername = event.changedTo)?.push()
    }

    private fun onShowPasswordClicked() {
        lastPushedState?.run {
            copy(hidePassword = !hidePassword)
        }?.push()
    }

    private inline fun withLastState(block: RegisterScreenViewState.() -> Unit) {
        lastPushedState?.run(block)
    }
}