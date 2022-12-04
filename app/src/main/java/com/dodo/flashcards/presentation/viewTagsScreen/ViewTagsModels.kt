package com.dodo.flashcards.presentation.viewTagsScreen

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState
import com.dodo.flashcards.domain.models.Tag

sealed interface ViewTagsViewEvent : ViewEvent {
    object ClickedNavigateUp : ViewTagsViewEvent
    object ClickedViewCards : ViewTagsViewEvent
    data class ToggledTag(val index: Int) : ViewTagsViewEvent
}

sealed interface ViewTagsViewState : ViewState {

    val continueButtonEnabled: Boolean

    object LoadErrorTags : ViewTagsViewState {
        override val continueButtonEnabled: Boolean = false
    }

    object LoadingTags : ViewTagsViewState {
        override val continueButtonEnabled: Boolean = false
    }

    data class LoadedTags(
        override val continueButtonEnabled: Boolean = false,
        val isFiltered: Boolean,
        val selectedIndices: Set<Int>,
        val tags: List<Tag>
    ) : ViewTagsViewState
}