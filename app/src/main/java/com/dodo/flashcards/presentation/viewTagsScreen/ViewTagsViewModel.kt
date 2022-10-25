package com.dodo.flashcards.presentation.viewTagsScreen

import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.usecases.authentication.GetUserUseCase
import com.dodo.flashcards.domain.usecases.authentication.LogoutUserUseCase
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.welcomeScreen.WelcomeScreenViewEvent
import com.dodo.flashcards.presentation.welcomeScreen.WelcomeScreenViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewTagsViewModel @Inject constructor(
) : BaseRoutingViewModel<ViewTagsViewState, ViewTagsViewEvent, MainDestination>() {
    override fun onEvent(event: ViewTagsViewEvent) {
        TODO("Not yet implemented")
    }
}