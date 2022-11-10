package com.dodo.flashcards.presentation.viewCardsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dodo.flashcards.presentation.common.FlashcardAppScaffold
import com.dodo.flashcards.presentation.common.ScreenBackground
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewState.*
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent.*
import com.dodo.flashcards.presentation.viewCardsScreen.subscreen.CardsLoadError
import com.dodo.flashcards.presentation.viewCardsScreen.subscreen.CardsLoaded


@Composable
fun ViewCardsViewDelegate(viewModel: ViewCardsViewModel) {
    FlashcardAppScaffold(
        onNavigateUp = { viewModel.onEvent(ClickedNavigateUp) },
        content = {
            ScreenBackground(scrollEnabled = false) {
                viewModel.viewState.collectAsState().value?.apply {
                    when (this) {
                        is CardsLoading -> CircularProgressIndicator()
                        is CardsLoaded -> CardsLoaded(
                            currentCard,
                            nextCard,
                            isFlipped,
                            viewModel
                        )
                        is CardsLoadError -> CardsLoadError(viewModel)
                    }
                }
            }
        }
    )
}