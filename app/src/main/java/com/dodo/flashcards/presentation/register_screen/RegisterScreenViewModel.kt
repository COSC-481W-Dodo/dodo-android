package com.dodo.flashcards.presentation.register_screen

import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.usecases.RegisterUserUseCase
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.register_screen.RegisterScreenViewEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : BaseRoutingViewModel<RegisterScreenViewState, RegisterScreenViewEvent, MainDestination>() {

    init {
        pushState(
            RegisterScreenViewState(
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
            viewModelScope.launch(Dispatchers.IO) {
                registerUserUseCase(textEmail, textPass)
                    .doOnSuccess {
                        // Todo: Route to LoginScreen
                    }
                    .doOnError {
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