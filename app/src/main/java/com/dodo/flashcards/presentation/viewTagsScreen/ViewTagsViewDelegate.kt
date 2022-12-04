package com.dodo.flashcards.presentation.viewTagsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.presentation.common.AppScaffold
import com.dodo.flashcards.presentation.common.ScreenBackground
import com.dodo.flashcards.presentation.theme.Typography
import com.dodo.flashcards.presentation.viewTagsScreen.ViewTagsViewState.*
import com.dodo.flashcards.presentation.viewTagsScreen.ViewTagsViewEvent.*
import com.dodo.flashcards.presentation.viewTagsScreen.subscreen.LoadErrorTags
import com.dodo.flashcards.presentation.viewTagsScreen.subscreen.LoadedTags
import com.dodo.flashcards.presentation.viewTagsScreen.subscreen.LoadingTags

@Composable
fun ViewTagsScreen(viewModel: ViewTagsViewModel) {
    AppScaffold(
        topBarTitle = "Select Tags",
        onNavigateUp = { viewModel.onEventDebounced(ClickedNavigateUp) },
        actions = {
            viewModel.viewState.collectAsState().value?.apply {
                IconButton(
                    modifier = Modifier.padding(16.dp),
                    onClick = {
                        viewModel.onEventDebounced(ClickedViewCards)
                    },
                    enabled = continueButtonEnabled,
                ) { Text(text = "CONTINUE", style = Typography.subtitle2) }
            }
        },
        content = {
            ScreenBackground(padding = 0.dp) {
                viewModel.viewState.collectAsState().value?.apply {
                    when (this) {
                        is LoadErrorTags -> LoadErrorTags()
                        is LoadingTags -> LoadingTags()
                        is LoadedTags -> LoadedTags(
                            tags,
                            selectedIndices,
                            viewModel
                        )
                    }
                }
            }
        }
    )
}