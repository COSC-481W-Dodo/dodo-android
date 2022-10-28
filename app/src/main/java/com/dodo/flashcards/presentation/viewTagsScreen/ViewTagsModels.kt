package com.dodo.flashcards.presentation.viewTagsScreen

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState
import com.dodo.flashcards.domain.models.Tag

sealed interface ViewTagsViewEvent : ViewEvent {

}

data class ViewTagsViewState(
    val selectedIndices: Set<Int>,
    val tags: List<Tag>,
) : ViewState