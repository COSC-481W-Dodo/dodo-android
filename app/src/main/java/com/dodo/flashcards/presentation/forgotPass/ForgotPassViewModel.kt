package com.dodo.flashcards.presentation.forgotPass

import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.usecases.authentication.ForgotPassSendEmailUseCase
import com.dodo.flashcards.presentation.forgotPass.ForgotPassViewState.*
import com.dodo.flashcards.presentation.forgotPass.ForgotPassViewEvent.*
import com.dodo.flashcards.presentation.MainDestination.*
import com.dodo.flashcards.presentation.MainDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPassViewModel @Inject constructor(
    private val forgotPassSendEmailUseCase: ForgotPassSendEmailUseCase
) : BaseRoutingViewModel<ForgotPassViewState, ForgotPassViewEvent, MainDestination>() {

    init {
        pushState(InputEmail(textEmail = String()))
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
                if (forgotPassSendEmailUseCase(textEmail)) {
                    PendingConfirmation(textEmail)
                } else {
                    InvalidEmail(textEmail)
                }.push()
            }
        }
    }

    private fun onClickedReturn() {
        routeTo(NavigateLogin)
    }

    private fun onTextChangedEmail(event: TextChangedEmail) {
        (lastPushedState as? InputEmail)?.copy(textEmail = event.changedTo)?.push()
    }
}