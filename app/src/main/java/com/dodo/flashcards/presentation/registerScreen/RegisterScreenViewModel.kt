package com.dodo.flashcards.presentation.registerScreen

import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.usecases.authentication.RegisterUserUseCase
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.MainDestination.*
import com.dodo.flashcards.presentation.registerScreen.RegisterScreenViewState.*
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
        Idle(
            hidePassword = false,
            textEmail = String(),
            textPass = String(),
            textUsername = String()
        ).push()
    }

    override fun onEvent(event: RegisterScreenViewEvent) {
        when (event) {
            is ClickedAcknowledgeVerification -> onClickedAcknowledgeVerification()
            is ClickedRegister -> onClickedRegister()
            is TextEmailChanged -> onTextEmailChanged(event)
            is TextPassChanged -> onTextPassChanged(event)
            is TextUsernameChanged -> onTextUsernameChanged(event)
            is ClickedShowPassword -> onShowPasswordClicked()
        }
    }

    private fun onClickedAcknowledgeVerification() {
        routeTo(NavigateWelcome)
    }

    private fun onClickedRegister() {
        withLastStateAsIdle {
            Loading.push()
            viewModelScope.launch(Dispatchers.IO) {
                registerUserUseCase(textEmail, textPass, textUsername)
                    .doOnSuccess {
                        withContext(Dispatchers.Main) {
                            Success(textEmail).push()
                        }
                    }
                    .doOnError {
                        this@withLastStateAsIdle.push()
                        // Todo: Send error ViewState
                    }
            }
        }
    }

    private fun onTextEmailChanged(event: TextEmailChanged) {
        withLastStateAsIdle {
            copy(textEmail = event.changedTo).push()
        }
    }

    private fun onTextPassChanged(event: TextPassChanged) {
        withLastStateAsIdle {
            copy(textPass = event.changedTo).push()
        }
    }

    private fun onTextUsernameChanged(event: TextUsernameChanged) {
        withLastStateAsIdle {
            copy(textUsername = event.changedTo).push()
        }
    }

    private fun onShowPasswordClicked() {
        withLastStateAsIdle {
            copy(hidePassword = !hidePassword).push()
        }
    }

    private inline fun withLastStateAsIdle(block: Idle.() -> Unit) {
        (lastPushedState as? Idle)?.run(block)
    }
}