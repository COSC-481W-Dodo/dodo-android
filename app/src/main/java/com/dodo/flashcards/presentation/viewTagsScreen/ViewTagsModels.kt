package com.dodo.flashcards.presentation.viewTagsScreen

import com.dodo.flashcards.architecture.ViewEvent
import com.dodo.flashcards.architecture.ViewState

sealed interface ViewTagsViewEvent : ViewEvent {

}

data class ViewTagsViewState(
    val selectedIndices: Set<Int>,
    val tags: List<String>,
) : ViewState