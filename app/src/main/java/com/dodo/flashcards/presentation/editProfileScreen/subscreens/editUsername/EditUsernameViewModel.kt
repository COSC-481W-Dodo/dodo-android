package com.dodo.flashcards.presentation.editProfileScreen.subscreens.editUsername

import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.usecases.authentication.GetUserUseCase
import com.dodo.flashcards.domain.usecases.authentication.UpdateUsernameUseCase
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.MainDestination.NavigateUp
import com.dodo.flashcards.presentation.editProfileScreen.subscreens.editUsername.EditUsernameViewEvent.*
import com.dodo.flashcards.util.UserUtils
import com.dodo.flashcards.util.doOnError
import com.dodo.flashcards.util.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditUsernameViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val userUtils: UserUtils,
    private val updateUsernameUseCase: UpdateUsernameUseCase,
) :
    BaseRoutingViewModel<EditUsernameViewState, EditUsernameViewEvent, MainDestination>() {

    private lateinit var lastSavedUsername: String

    init {
        viewModelScope.launch {
            getUserUseCase()
                .doOnSuccess {
                    lastSavedUsername = data.username
                    EditUsernameViewState(
                        confirmButtonEnabled = false,
                        textUsername = lastSavedUsername,
                        hasSuccessfullySet = false
                    ).push()
                }
        }
    }

    override fun onEvent(event: EditUsernameViewEvent) {
        when (event) {
            is ClickedConfirm -> onClickedConfirm()
            is ClickedReturn -> onClickedReturn()
            is TextUsernameChanged -> onTextUsernameChanged(event)
        }
    }

    private fun onClickedConfirm() {
        viewModelScope.launch(Dispatchers.IO) {
            lastPushedState?.apply {
                copy(confirmButtonEnabled = false).push()
                updateUsernameUseCase(textUsername)
                    .doOnSuccess {
                        lastSavedUsername = textUsername
                        copy(
                            confirmButtonEnabled = false,
                            hasSuccessfullySet = true
                        ).push()
                    }
                    .doOnError {
                        // Todo, add some error response in ui
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

    private fun onTextUsernameChanged(event: TextUsernameChanged) {
        lastPushedState?.copy(
            confirmButtonEnabled = userUtils.isValidUsername(
                username = event.changedTo,
                oldUsername = lastSavedUsername
            ),
            textUsername = event.changedTo,
            hasSuccessfullySet = false
        )?.push()
    }
}