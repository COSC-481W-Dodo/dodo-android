package com.dodo.flashcards.presentation.forgotPassScreen

import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.usecases.authentication.ForgotPassSendEmailUseCase
import com.dodo.flashcards.presentation.forgotPassScreen.ForgotPassViewState.*
import com.dodo.flashcards.presentation.forgotPassScreen.ForgotPassViewEvent.*
import com.dodo.flashcards.presentation.MainDestination.*
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.util.doOnError
import com.dodo.flashcards.util.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPassViewModel @Inject constructor(
    private val forgotPassSendEmailUseCase: ForgotPassSendEmailUseCase
) : BaseRoutingViewModel<ForgotPassViewState, ForgotPassViewEvent, MainDestination>() {

    init {
        pushState(InputEmail(
            isError = false,
            textEmail = String()
        ))
    }

    override fun onEvent(event: ForgotPassViewEvent) {
        when (event) {
            is ClickedConfirmEmail -> onClickedConfirmEmail()
            is ClickedReturn -> onClickedReturn()
            is TextChangedEmail -> onTextChangedEmail(event)
        }
    }

    private fun onClickedConfirmEmail() {
        viewModelScope.launch(Dispatchers.IO) {
            lastPushedState?.apply {
                Loading(textEmail).push()
                forgotPassSendEmailUseCase(textEmail)
                    .doOnSuccess {
                        PendingConfirmation(textEmail).push()
                    }
                    .doOnError {
                        (this@apply as? InputEmail)?.copy(
                            isError = true
                        )?.push()
                    }
            }
        }
    }

    private fun onClickedReturn() {
        routeTo(NavigateLogin)
    }

    private fun onTextChangedEmail(event: TextChangedEmail) {
        (lastPushedState as? InputEmail)?.copy(
            isError = false,
            textEmail = event.changedTo
        )?.push()
    }
}