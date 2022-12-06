package com.dodo.flashcards.presentation.viewTagsScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.usecases.flashcards.GetTagsUseCase
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.MainDestination.NavigateUp
import com.dodo.flashcards.presentation.MainDestination.NavigateViewCards
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
    savedStateHandle: SavedStateHandle
) : BaseRoutingViewModel<ViewTagsViewState, ViewTagsViewEvent, MainDestination>() {

    private val ownerOnly = (savedStateHandle.get<Boolean>("ownerOnly") ?: "Missing owner only") as Boolean

    init {
        LoadingTags.push()

        viewModelScope.launch(Dispatchers.IO) {
            getTagsUseCase(ownerOnly = ownerOnly)
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
        }
    }

    private fun onClickedNavigateUp() {
        routeTo(NavigateUp)
    }

    private fun onClickedViewCards() {
        (lastPushedState as? LoadedTags)?.apply {
            routeTo(NavigateViewCards(
                tags
                    .filterIndexed { index, _ -> selectedIndices.contains(index) }
                    .map { it.value },
                ownerOnly = ownerOnly
            ))
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