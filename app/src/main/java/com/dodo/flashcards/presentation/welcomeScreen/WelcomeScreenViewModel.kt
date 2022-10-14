package com.dodo.flashcards.presentation.welcomeScreen

import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.usecases.authentication.LogoutUserUseCase
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.MainDestination.*
import com.dodo.flashcards.presentation.welcomeScreen.WelcomeScreenViewEvent.ClickedEditProfile
import com.dodo.flashcards.presentation.welcomeScreen.WelcomeScreenViewEvent.ClickedLogout
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeScreenViewModel @Inject constructor(
    private val logoutUserUseCase: LogoutUserUseCase,
    firebaseAuth: FirebaseAuth,
) : BaseRoutingViewModel<WelcomeScreenViewState, WelcomeScreenViewEvent, MainDestination>() {

    init {
        WelcomeScreenViewState(firebaseAuth.currentUser?.displayName).push()
    }

    override fun onEvent(event: WelcomeScreenViewEvent) {
        when (event) {
            is ClickedEditProfile -> onClickedEditProfile()
            is ClickedLogout -> onClickedLogout()
        }
    }

    private fun onClickedEditProfile() {
        routeTo(NavigateEditProfile)
    }

    private fun onClickedLogout() {
        viewModelScope.launch {
            logoutUserUseCase()
            routeTo(NavigateLogin)
        }
    }
}