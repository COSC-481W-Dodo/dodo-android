package com.dodo.flashcards.presentation.editProfileScreen.subscreens.editPass

import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.MainDestination.NavigateUp
import com.dodo.flashcards.presentation.editProfileScreen.subscreens.editPass.EditPassViewEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditPassViewModel @Inject constructor() :
    BaseRoutingViewModel<EditPassViewState, EditPassViewEvent, MainDestination>() {

    init {
        pushState(EditPassViewState(textPass = String()))
    }

    override fun onEvent(event: EditPassViewEvent) {
        when (event) {
            is ClickedConfirm -> onClickedConfirm()
            is ClickedReturn -> onClickedReturn()
            is TextPassChanged -> onTextPassChanged(event)
        }
    }

    private fun onClickedConfirm() {

    }

    private fun onClickedReturn() {
        routeTo(NavigateUp)
    }

    private fun onTextPassChanged(event: TextPassChanged) {
        lastPushedState?.copy(textPass = event.changedTo)?.push()
    }
}