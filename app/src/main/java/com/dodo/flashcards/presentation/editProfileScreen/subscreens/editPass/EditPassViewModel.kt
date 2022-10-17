package com.dodo.flashcards.presentation.editProfileScreen.subscreens.editPass

import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.usecases.authentication.UpdatePasswordUseCase
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.MainDestination.NavigateUp
import com.dodo.flashcards.presentation.editProfileScreen.subscreens.editPass.EditPassViewEvent.*
import com.dodo.flashcards.util.UserUtils
import com.dodo.flashcards.util.doOnError
import com.dodo.flashcards.util.doOnSuccess
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import javax.inject.Inject

@HiltViewModel
class EditPassViewModel @Inject constructor(
    private val userUtils: UserUtils,
    private val updatePasswordUseCase: UpdatePasswordUseCase
) :
    BaseRoutingViewModel<EditPassViewState, EditPassViewEvent, MainDestination>() {

    init {
        EditPassViewState().push()
    }

    override fun onEvent(event: EditPassViewEvent) {
        when (event) {
            is ClickedConfirm -> onClickedConfirm()
            is ClickedReturn -> onClickedReturn()
            is ClickedShowPasswordOld -> onClickedShowPasswordOld()
            is ClickedShowPasswordNew -> onClickedShowPasswordNew()
            is TextPassNewChanged -> onTextPassNewChanged(event)
            is TextPassOldChanged -> onTextPassOldChanged(event)
        }
    }

    private fun onClickedConfirm() {
        viewModelScope.launch(Dispatchers.IO) {
            var errorMessagePassNew: String? = null
            var errorMessagePassOld: String? = null
            lastPushedState?.apply {
                copy(clickEventsEnabled = false).push()
                if (!userUtils.isValidPassword(textPassNew)) {
                    errorMessagePassNew = "This is not a valid password."
                }
                updatePasswordUseCase(
                    oldPassword = textPassOld,
                    newPassword = textPassNew
                )
                    .doOnSuccess {
                        copy(
                            clickEventsEnabled = true,
                            hasSuccessfullySet = true
                        ).push()
                    }
                    .doOnError {
                        if (exception is FirebaseAuthInvalidCredentialsException) {
                            errorMessagePassOld = "This is not the current password used by this account."
                        } else if (textPassOld.isEmpty()) {
                            errorMessagePassOld = "Cannot be empty."
                        }
                        copy(
                            errorMessagePassNew = errorMessagePassNew,
                            errorMessagePassOld = errorMessagePassOld,
                            clickEventsEnabled = true,
                            hasSuccessfullySet = false
                        ).push()
                    }
            }
        }
    }

    private fun onClickedReturn() {
        routeTo(NavigateUp)
    }

    private fun onClickedShowPasswordOld() {
        lastPushedState?.run {
            copy(passHiddenOld = !passHiddenOld)
        }?.push()
    }

    private fun onClickedShowPasswordNew() {
        lastPushedState?.run {
            copy(passHiddenNew = !passHiddenNew)
        }?.push()
    }

    private fun onTextPassNewChanged(event: TextPassNewChanged) {
        lastPushedState?.copy(
            errorMessagePassNew = null,
            hasSuccessfullySet = false,
            textPassNew = event.changedTo
        )?.push()
    }

    private fun onTextPassOldChanged(event: TextPassOldChanged) {
        lastPushedState?.copy(
            errorMessagePassOld = null,
            hasSuccessfullySet = false,
            textPassOld = event.changedTo
        )?.push()
    }
}