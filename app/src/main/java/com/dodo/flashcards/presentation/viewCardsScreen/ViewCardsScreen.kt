package com.dodo.flashcards.presentation.viewCardsScreen

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.dodo.flashcards.presentation.common.AppScaffold
import com.dodo.flashcards.presentation.common.ScreenBackground
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent.ClickedNavigateUp
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewState.*
import com.dodo.flashcards.presentation.viewCardsScreen.subscreen.CardsLoadError
import com.dodo.flashcards.presentation.viewCardsScreen.subscreen.CardsLoaded

@Composable
fun ViewCardsScreen(viewModel: ViewCardsViewModel) {
    AppScaffold(
        topBarTitle = "",
        onNavigateUp = { viewModel.onEventDebounced(ClickedNavigateUp) },
        actions = {},
    ) {
        ScreenBackground(scrollEnabled = false) {
            viewModel.viewState.collectAsState().value?.apply {
                when (this) {
                    is CardsLoading -> CircularProgressIndicator()
                    is CardsLoaded -> CardsLoaded(
                        currentCard = currentCard,
                        nextCard = nextCard,
                        isFlipped = isFlipped,
                        eventReceiver = viewModel
                    )
                    is CardsLoadError -> CardsLoadError(viewModel)
                }
            }
        }
    }
}