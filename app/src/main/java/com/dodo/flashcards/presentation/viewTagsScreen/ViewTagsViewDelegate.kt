package com.dodo.flashcards.presentation.viewTagsScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.dodo.flashcards.presentation.common.ScreenBackground
import com.dodo.flashcards.presentation.viewTagsScreen.ViewTagsViewState.*
import com.dodo.flashcards.presentation.viewTagsScreen.subscreen.LoadErrorTags
import com.dodo.flashcards.presentation.viewTagsScreen.subscreen.LoadedTags
import com.dodo.flashcards.presentation.viewTagsScreen.subscreen.LoadingTags

@Composable
fun ViewTagsScreen(viewModel: ViewTagsViewModel) {
    ScreenBackground {
        viewModel.viewState.collectAsState().value?.apply {
            when (this) {
                is LoadErrorTags -> LoadErrorTags()
                is LoadingTags -> LoadingTags()
                is LoadedTags -> LoadedTags(tags, selectedIndices, viewModel)
            }
        }
    }
}