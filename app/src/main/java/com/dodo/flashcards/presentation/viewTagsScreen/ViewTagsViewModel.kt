package com.dodo.flashcards.presentation.viewTagsScreen

import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.usecases.authentication.GetUserUseCase
import com.dodo.flashcards.domain.usecases.authentication.LogoutUserUseCase
import com.dodo.flashcards.domain.usecases.flashcards.GetTagsUseCase
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.welcomeScreen.WelcomeScreenViewEvent
import com.dodo.flashcards.presentation.welcomeScreen.WelcomeScreenViewState
import com.dodo.flashcards.util.doOnError
import com.dodo.flashcards.util.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewTagsViewModel @Inject constructor(
    getTagsUseCase: GetTagsUseCase
) : BaseRoutingViewModel<ViewTagsViewState, ViewTagsViewEvent, MainDestination>() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getTagsUseCase()
                .doOnSuccess {
                    ViewTagsViewState(setOf(), data).push()
                }
                .doOnError { }
        }
    }

    override fun onEvent(event: ViewTagsViewEvent) {
        TODO("Not yet implemented")
    }
}