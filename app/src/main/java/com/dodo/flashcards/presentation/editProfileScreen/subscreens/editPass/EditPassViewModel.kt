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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditPassViewModel @Inject constructor(
    private val userUtils: UserUtils,
    private val updatePasswordUseCase: UpdatePasswordUseCase
) :
    BaseRoutingViewModel<EditPassViewState, EditPassViewEvent, MainDestination>() {

    init {
        pushState(
            EditPassViewState(
                confirmButtonEnabled = false,
                hasSuccessfullySet = false,
                textPassNew = String(),
                textPassOld = String(),
                passHiddenOld = false,
                passHiddenNew = false
            )
        )
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
            lastPushedState?.apply {
                copy(confirmButtonEnabled = false).push()
                updatePasswordUseCase(
                    oldPassword = textPassOld,
                    newPassword = textPassNew
                )
                    .doOnSuccess {
                        copy(
                            confirmButtonEnabled = false,
                            hasSuccessfullySet = true
                        ).push()
                    }
                    .doOnError {
                        // Todo, add some error response in ui
                        // Todo, parse each exception and add a "try again error"
                        // for FirebaseTooManyRequestsException
                        copy(
                            confirmButtonEnabled = true,
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

    }

    private fun onClickedShowPasswordNew() {

    }

    private fun onTextPassNewChanged(event: TextPassNewChanged) {
        lastPushedState?.copy(
            confirmButtonEnabled = userUtils.isValidPassword(event.changedTo),
            hasSuccessfullySet = false,
            textPassNew = event.changedTo
        )?.push()
    }

    private fun onTextPassOldChanged(event: TextPassOldChanged) {
        lastPushedState?.copy(
            hasSuccessfullySet = false,
            textPassOld = event.changedTo
        )?.push()
    }
}