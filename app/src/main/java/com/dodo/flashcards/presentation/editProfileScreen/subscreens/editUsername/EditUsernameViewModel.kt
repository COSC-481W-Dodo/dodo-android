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
                    EditUsernameViewState(textUsername = lastSavedUsername).push()
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
                copy(buttonsEnabled = false).push()
                if (textUsername == lastSavedUsername) {
                    copy(
                        buttonsEnabled = true,
                        errorMessage = "This is your current username.",
                        hasSuccessfullySet = false
                    ).push()
                    return@launch
                } else if (!userUtils.isValidUsername(
                        username = textUsername,
                        oldUsername = lastSavedUsername
                    )
                ) {
                    copy(
                        buttonsEnabled = true,
                        errorMessage = "This is not a valid username.",
                        hasSuccessfullySet = false
                    ).push()
                    return@launch
                }
                updateUsernameUseCase(textUsername)
                    .doOnSuccess {
                        lastSavedUsername = textUsername
                        copy(
                            buttonsEnabled = false,
                            hasSuccessfullySet = true
                        ).push()
                    }
                    .doOnError {
                        // Todo, add some error response in ui
                        copy(
                            buttonsEnabled = true,
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
            errorMessage = null,
            textUsername = event.changedTo,
            hasSuccessfullySet = false
        )?.push()
    }
}