package com.dodo.flashcards.presentation.viewTagsScreen

import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.models.AuthRepository
import com.dodo.flashcards.domain.usecases.authentication.GetUserIdUseCase
import com.dodo.flashcards.domain.usecases.flashcards.GetTagsUseCase
import com.dodo.flashcards.domain.usecases.flashcards.GetUserTagsUseCase
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.MainDestination.NavigateUp
import com.dodo.flashcards.presentation.MainDestination.NavigateViewCards
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewModel
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewState
import com.dodo.flashcards.presentation.viewTagsScreen.ViewTagsViewEvent.*
import com.dodo.flashcards.presentation.viewTagsScreen.ViewTagsViewState.*
import com.dodo.flashcards.util.doOnError
import com.dodo.flashcards.util.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewTagsViewModel @Inject constructor(
    private val getTagsUseCase: GetTagsUseCase,
    private val getUserTagsUseCase: GetUserTagsUseCase,
    private val getUserIdUseCase: GetUserIdUseCase
) : BaseRoutingViewModel<ViewTagsViewState, ViewTagsViewEvent, MainDestination>() {

    init {
        LoadingTags.push()
        viewModelScope.launch(Dispatchers.IO) {
            getTagsUseCase()
                .doOnSuccess {
                    LoadedTags(
                        continueButtonEnabled = false,
                        selectedIndices = setOf(),
                        tags = data,
                        isFiltered = false
                    ).push()
                }
                .doOnError { LoadErrorTags.push() }
        }
    }

    override fun onEvent(event: ViewTagsViewEvent) {
        when (event) {
            is ClickedNavigateUp -> onClickedNavigateUp()
            is ClickedViewCards -> onClickedViewCards()
            is ToggledTag -> onToggledTag(event)
            is ClickedFilterTags -> onClickedFilterTags()
        }
    }

    private fun onClickedNavigateUp() {
        routeTo(NavigateUp)
    }

    private fun resetTags() {
        LoadingTags.push()
        viewModelScope.launch(Dispatchers.IO) {
            getTagsUseCase()
                .doOnSuccess {
                    LoadedTags(
                        continueButtonEnabled = false,
                        selectedIndices = setOf(),
                        tags = data,
                        isFiltered = false
                    ).push()
                }
                .doOnError { LoadErrorTags.push() }
        }
    }

    private fun onClickedViewCards() {
        (lastPushedState as? LoadedTags)?.apply {
            // Todo, will need to make this the tag id instead of name
            routeTo(NavigateViewCards(
                tags
                    .filterIndexed { index, _ -> selectedIndices.contains(index) }
                    .map { it.value }
            ))
        }
    }

    private fun onClickedFilterTags() {
        (lastPushedState as? LoadedTags)?.apply {
            when (isFiltered) {
                true -> resetTags()
                false -> {
                    LoadingTags.push()
                    var userId = String()
                    viewModelScope.launch(Dispatchers.IO) {
                        getUserIdUseCase().doOnSuccess {
                            userId = data
                        }.doOnError {
                            //TODO change this to a specific error state
                            LoadErrorTags.push()
                        }
                        getUserTagsUseCase(userId).doOnSuccess {
                            LoadedTags(
                                continueButtonEnabled = false,
                                selectedIndices = setOf(),
                                tags = data,
                                isFiltered = true
                            ).push()
                        }.doOnError {
                            LoadErrorTags.push()
                        }
                    }
                }
            }
        }
    }

    private fun onToggledTag(event: ToggledTag) {
        val index = event.index
        (lastPushedState as? LoadedTags)?.run {
            val newSelectedIndices = selectedIndices.toMutableSet().let {
                if (it.contains(index)) it - index else it + index
            }
            copy(
                continueButtonEnabled = newSelectedIndices.size in 1..10,
                selectedIndices = newSelectedIndices
            )
        }?.push()
    }


}