package com.dodo.flashcards.presentation.editProfileScreen.subscreens.editUsername

import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.MainDestination.NavigateUp
import com.dodo.flashcards.presentation.editProfileScreen.subscreens.editUsername.EditUsernameViewEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditUsernameViewModel @Inject constructor() :
    BaseRoutingViewModel<EditUsernameViewState, EditUsernameViewEvent, MainDestination>() {

    init {
        EditUsernameViewState(textUsername = String()).push()
    }

    override fun onEvent(event: EditUsernameViewEvent) {
        when (event) {
            is ClickedConfirm -> onClickedConfirm()
            is ClickedReturn -> onClickedReturn()
            is TextUsernameChanged -> onTextUsernameChanged(event)
        }
    }

    private fun onClickedConfirm() {

    }

    private fun onClickedReturn() {
        routeTo(NavigateUp)
    }

    private fun onTextUsernameChanged(event: TextUsernameChanged) {
        lastPushedState?.copy(textUsername = event.changedTo)?.push()
    }
}