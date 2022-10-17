package com.dodo.flashcards.presentation.editProfileScreen

import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.MainDestination.*
import com.dodo.flashcards.presentation.editProfileScreen.EditProfileViewEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor() :
    BaseRoutingViewModel<EditProfileViewState, EditProfileViewEvent, MainDestination>() {

    init {
        pushState(EditProfileViewState)
    }

    override fun onEvent(event: EditProfileViewEvent) {
        when (event) {
            is ClickedEditEmail -> onClickedEditEmail()
            is ClickedEditPassword -> onClickedEditPassword()
            is ClickedEditUsername -> onClickedEditUsername()
            is ClickedReturn -> onClickedReturn()
        }
    }

    private fun onClickedEditEmail() {
        routeTo(NavigateEditEmail)
    }

    private fun onClickedEditPassword() {
        routeTo(NavigateEditPass)
    }

    private fun onClickedEditUsername() {
        routeTo(NavigateEditUsername)
    }

    private fun onClickedReturn() {
        routeTo(NavigateUp)
    }
}