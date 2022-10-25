package com.dodo.flashcards.presentation.registerScreen

import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.usecases.authentication.RegisterUserUseCase
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.MainDestination.*
import com.dodo.flashcards.presentation.registerScreen.RegisterScreenViewState.*
import com.dodo.flashcards.presentation.registerScreen.RegisterScreenViewEvent.*
import com.dodo.flashcards.util.UserUtils
import com.dodo.flashcards.util.doOnError
import com.dodo.flashcards.util.doOnSuccess
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val userUtils: UserUtils
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
        var errorEmailMessage: String? = null
        var errorPasswordMessage: String? = null
        var errorUsernameMessage: String? = null
        withLastStateAsIdle {
            Loading.push()
            if (textEmail.isEmpty()) {
                errorEmailMessage = "Cannot be empty."
            } else if (!userUtils.isValidEmail(textEmail)) {
                errorEmailMessage = "This is not a valid e-mail."
            }
            if (textUsername.isEmpty()) {
                errorUsernameMessage = "Cannot be empty."
            } else if (!userUtils.isValidUsername(textUsername)) {
                errorUsernameMessage = "This is not a valid username."
            }
            if (textPass.isEmpty()) {
                errorPasswordMessage = "Cannot be empty."
            } else if (!userUtils.isValidPassword(textPass)) {
                errorPasswordMessage = "This is not a valid password."
            }
            if (errorEmailMessage != null || errorPasswordMessage != null || errorUsernameMessage != null) {
                copy(
                    errorEmailMessage = errorEmailMessage,
                    errorPasswordMessage = errorPasswordMessage,
                    errorUsernameMessage = errorUsernameMessage
                ).push()
                return
            }
            viewModelScope.launch(Dispatchers.IO) {
                registerUserUseCase(textEmail, textPass, textUsername)
                    .doOnSuccess {
                        withContext(Dispatchers.Main) {
                            Success(textEmail).push()
                        }
                    }
                    .doOnError {
                        if (exception is FirebaseAuthUserCollisionException) {
                            this@withLastStateAsIdle.copy(
                                errorEmailMessage = "This e-mail is already registered with Dodo."
                            ).push()
                        }
                        // Todo: Send error ViewState
                    }
            }
        }
    }

    private fun onTextEmailChanged(event: TextEmailChanged) {
        withLastStateAsIdle {
            copy(
                errorEmailMessage = null,
                textEmail = event.changedTo
            ).push()
        }
    }

    private fun onTextPassChanged(event: TextPassChanged) {
        withLastStateAsIdle {
            copy(
                errorPasswordMessage = null,
                textPass = event.changedTo
            ).push()
        }
    }

    private fun onTextUsernameChanged(event: TextUsernameChanged) {
        withLastStateAsIdle {
            copy(
                errorUsernameMessage = null,
                textUsername = event.changedTo,
            ).push()
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