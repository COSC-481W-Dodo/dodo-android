package com.dodo.flashcards.presentation.viewCardsScreen

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.dodo.flashcards.presentation.common.ScreenBackground
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewState.*
import com.dodo.flashcards.presentation.viewCardsScreen.subscreen.CardsLoaded


@Composable
fun ViewCardsScreen(viewModel: ViewCardsViewModel) {
    ScreenBackground(scrollEnabled = false) {
        viewModel.viewState.collectAsState().value?.apply {
            when (this) {
                is CardsLoading -> CircularProgressIndicator()
                is CardsLoaded -> CardsLoaded(
                    currentCardBack,
                    currentCardFront,
                    currentCardIndex,
                    currentCardIsFlipped,
                    nextCardFront,
                    viewModel
                )
                is CardsLoadError -> { // todo
                }
            }
        }
    }
}

