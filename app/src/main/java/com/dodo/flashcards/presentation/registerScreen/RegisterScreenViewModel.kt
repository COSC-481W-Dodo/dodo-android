package com.dodo.flashcards.presentation.registerScreen

import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.usecases.authentication.RegisterUserUseCase
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.MainDestination.*
import com.dodo.flashcards.presentation.registerScreen.RegisterScreenViewEvent.*
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
                textEmail = String(),
                textPass = String()
            )
        )
    }

    override fun onEvent(event: RegisterScreenViewEvent) {
        when (event) {
            is ClickedRegister -> onClickedRegister()
            is TextEmailChanged -> onTextEmailChanged(event)
            is TextPassChanged -> onTextPassChanged(event)
        }
    }

    private fun onClickedRegister() {
        withLastState {
            copy(buttonsEnabled = false).push()
            viewModelScope.launch(Dispatchers.IO) {
                registerUserUseCase(textEmail, textPass)
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
        withLastState {
            copy(textEmail = event.changedTo).push()
        }
    }

    private fun onTextPassChanged(event: TextPassChanged) {
        withLastState {
            copy(textPass = event.changedTo).push()
        }
    }

    private inline fun withLastState(block: RegisterScreenViewState.() -> Unit) {
        lastPushedState?.run(block)
    }
}