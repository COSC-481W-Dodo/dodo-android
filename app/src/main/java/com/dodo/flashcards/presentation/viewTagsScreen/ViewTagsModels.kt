package com.dodo.flashcards.presentation.viewTagsScreen

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState
import com.dodo.flashcards.domain.models.Tag

sealed interface ViewTagsViewEvent : ViewEvent {
    object ClickedViewCards : ViewTagsViewEvent
    data class ToggledTag(val index: Int) : ViewTagsViewEvent
}

sealed interface ViewTagsViewState : ViewState {
    object LoadErrorTags : ViewTagsViewState
    object LoadingTags : ViewTagsViewState
    data class LoadedTags(
        val selectedIndices: Set<Int>,
        val tags: List<Tag>
    ) : ViewTagsViewState
}