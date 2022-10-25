package com.dodo.flashcards.presentation.loginScreen

import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.usecases.authentication.LoginUserUseCase
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.MainDestination.NavigateForgotPass
import com.dodo.flashcards.presentation.MainDestination.NavigateRegister
import com.dodo.flashcards.presentation.MainDestination.NavigateWelcome
import com.dodo.flashcards.presentation.loginScreen.LoginScreenViewEvent.*
import com.dodo.flashcards.util.doOnError
import com.dodo.flashcards.util.doOnSuccess
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase
) : BaseRoutingViewModel<LoginScreenViewState, LoginScreenViewEvent, MainDestination>() {

    init {
        pushState(
            LoginScreenViewState(
                errorEmailMessage = null,
                errorPasswordMessage = null,
                buttonsEnabled = true,
                textEmail = String(),
                textPass = String(),
                passHidden = false
            )
        )
    }

    override fun onEvent(event: LoginScreenViewEvent) {
        when (event) {
            is ClickedForgotPassword -> onClickedForgotPassword()
            is ClickedLogin -> onClickedLogin()
            is ClickedRegister -> onClickedRegister()
            is TextEmailChanged -> onTextEmailChanged(event)
            is TextPassChanged -> onTextPassChanged(event)
            is ClickedShowPassword -> onShowPasswordClicked()
        }
    }

    private fun onClickedForgotPassword() {
        routeTo(NavigateForgotPass)
    }

    private fun onClickedLogin() {
        viewModelScope.launch(Dispatchers.IO) {
            withLastState {
                copy(buttonsEnabled = false).push()
                loginUserUseCase(textEmail, textPass)
                    .doOnSuccess {
                        // Switch thread back to Main for navigation, else crash
                        withContext(Dispatchers.Main) {
                            routeTo(NavigateWelcome)
                        }
                    }
                    .doOnError {
                        // Todo some more error stuff
                        println("Here $exception")
                        when (exception) {
                            is FirebaseAuthInvalidCredentialsException -> {
                                if (exception.localizedMessage?.contains("password") == true) {
                                    copy(
                                        buttonsEnabled = true,
                                        errorPasswordMessage = "Invalid password."
                                    ).push()
                                } else {
                                    println("here invalid email cred")
                                    // todo could do more here
                                    copy(
                                        buttonsEnabled = true,
                                        errorEmailMessage = "Invalid email format."
                                    ).push()
                                }
                            }
                            is FirebaseAuthInvalidUserException -> {
                                copy(
                                    buttonsEnabled = true,
                                    errorEmailMessage = "This is not a registered email."
                                ).push()
                            }
                            is IllegalArgumentException -> {
                                copy(
                                    buttonsEnabled = true,
                                    errorEmailMessage = if (textEmail == "") "Cannot be empty" else null,
                                    errorPasswordMessage = if (textPass == "") "Cannot be empty" else null
                                ).push()
                            }
                        }
                    }
            }
        }
    }

    private fun onClickedRegister() {
        routeTo(NavigateRegister)
    }

    private fun onShowPasswordClicked() {
        lastPushedState?.run {
            copy(passHidden = !passHidden)
        }?.push()
    }

    private fun onTextEmailChanged(event: TextEmailChanged) {
        lastPushedState?.copy(
            errorEmailMessage = null,
            textEmail = event.changedTo
        )?.push()
    }

    private fun onTextPassChanged(event: TextPassChanged) {
        lastPushedState?.copy(
            errorPasswordMessage = null,
            textPass = event.changedTo
        )?.push()
    }

    private inline fun withLastState(block: LoginScreenViewState.() -> Unit) {
        lastPushedState?.run(block)
    }
}