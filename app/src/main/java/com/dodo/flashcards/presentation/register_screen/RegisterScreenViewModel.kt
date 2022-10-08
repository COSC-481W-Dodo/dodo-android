package com.dodo.flashcards.presentation.register_screen

import androidx.lifecycle.SavedStateHandle
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.register_screen.RegisterScreenViewEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseRoutingViewModel<RegisterScreenViewState, RegisterScreenViewEvent, MainDestination>() {

    override fun onEvent(event: RegisterScreenViewEvent) {
        when (event) {
            is ClickedRegister -> onClickedRegister()
            is TextEmailChanged -> onTextEmailChanged(event)
            is TextPassChanged -> onTextPassChanged(event)
        }
    }

    private fun onClickedRegister() {

    }

    private fun onTextEmailChanged(event: TextEmailChanged) {

    }

    private fun onTextPassChanged(event: TextPassChanged) {

    }
}