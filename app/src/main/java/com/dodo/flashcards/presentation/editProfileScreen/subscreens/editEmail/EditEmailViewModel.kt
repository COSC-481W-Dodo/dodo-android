package com.dodo.flashcards.presentation.editProfileScreen.subscreens.editEmail

import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.usecases.authentication.UpdateEmailUseCase
import com.dodo.flashcards.domain.usecases.authentication.UpdatePasswordUseCase
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.MainDestination.*
import com.dodo.flashcards.presentation.editProfileScreen.subscreens.editEmail.EditEmailViewState.*
import com.dodo.flashcards.presentation.editProfileScreen.subscreens.editEmail.EditEmailViewEvent.*
import com.dodo.flashcards.presentation.editProfileScreen.subscreens.editPass.EditPassViewEvent
import com.dodo.flashcards.presentation.editProfileScreen.subscreens.editPass.EditPassViewState
import com.dodo.flashcards.util.UserUtils
import com.dodo.flashcards.util.doOnError
import com.dodo.flashcards.util.doOnSuccess
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditEmailViewModel @Inject constructor(
    private val userUtils: UserUtils,
    private val updateEmailUseCase: UpdateEmailUseCase
) :
    BaseRoutingViewModel<EditEmailViewState, EditEmailViewEvent, MainDestination>() {

    init {
        EditEmailViewState().push()
    }

    override fun onEvent(event: EditEmailViewEvent) {
        when (event) {
            is ClickedConfirm -> onClickedConfirm()
            is ClickedReturn -> onClickedReturn()
            is ClickedShowPassword -> onClickedShowPassword()
            is TextEmailChanged -> onTextEmailChanged(event)
            is TextPassChanged -> onTextPassChanged(event)
        }
    }


    private fun onClickedConfirm() {
        viewModelScope.launch(Dispatchers.IO) {
            lastPushedState?.apply {
                copy(confirmButtonEnabled = false).push()
                updateEmailUseCase(
                    newEmail = textEmail,
                    password = textPass
                )
                    .doOnSuccess {
                        copy(
                            confirmButtonEnabled = false,
                            hasSuccessfullySet = true
                        ).push()
                    }
                    .doOnError {
                        if (exception is FirebaseAuthInvalidCredentialsException) {
                            copy(
                                errorPassMessage = "Invalid password.",
                                confirmButtonEnabled = true,
                                hasSuccessfullySet = false
                            ).push()
                        } else {
                            copy(
                                confirmButtonEnabled = true,
                                hasSuccessfullySet = false
                            ).push()
                        }
                    }
            }
        }
    }

    private fun onClickedReturn() {
        routeTo(NavigateUp)
    }

    private fun onClickedShowPassword() {
        lastPushedState?.run { copy(passHidden = !passHidden) }?.push()
    }

    private fun onTextPassChanged(event: TextPassChanged) {
        lastPushedState?.run {
            copy(
                confirmButtonEnabled = userUtils.isValidPassword(event.changedTo) && userUtils.isValidEmail(
                    textEmail
                ),
                errorPassMessage = null,
                hasSuccessfullySet = false,
                textPass = event.changedTo
            )
        }?.push()
    }

    private fun onTextEmailChanged(event: TextEmailChanged) {
        lastPushedState?.run {
            copy(
                confirmButtonEnabled = userUtils.isValidPassword(textPass) && userUtils.isValidEmail(
                    event.changedTo
                ),
                hasSuccessfullySet = false,
                textEmail = event.changedTo
            )
        }?.push()
    }
}
