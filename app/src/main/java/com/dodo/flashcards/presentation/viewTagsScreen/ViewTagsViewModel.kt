package com.dodo.flashcards.presentation.viewTagsScreen

import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.usecases.authentication.GetUserUseCase
import com.dodo.flashcards.domain.usecases.authentication.LogoutUserUseCase
import com.dodo.flashcards.domain.usecases.flashcards.GetTagsUseCase
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.welcomeScreen.WelcomeScreenViewEvent
import com.dodo.flashcards.presentation.welcomeScreen.WelcomeScreenViewState
import com.dodo.flashcards.presentation.viewTagsScreen.ViewTagsViewState.*
import com.dodo.flashcards.presentation.viewTagsScreen.ViewTagsViewEvent.*
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
        LoadingTags.push()
        viewModelScope.launch(Dispatchers.IO) {
            getTagsUseCase()
                .doOnSuccess {
                    LoadedTags(
                        selectedIndices = setOf(),
                        tags = data
                    ).push()
                }
                .doOnError { LoadErrorTags.push() }
        }
    }

    override fun onEvent(event: ViewTagsViewEvent) {
        when (event) {
            is ToggledTag -> onToggledTag(event)
        }
    }

    private fun onToggledTag(event: ToggledTag) {
        val index = event.index
        (lastPushedState as? LoadedTags)?.run {
            copy(selectedIndices = selectedIndices.toMutableSet().let {
                if (it.contains(index)) it - index else it + index
            })
        }?.push()
    }
}