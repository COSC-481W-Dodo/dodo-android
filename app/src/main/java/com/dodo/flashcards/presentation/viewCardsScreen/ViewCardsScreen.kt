package com.dodo.flashcards.presentation.viewCardsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Undo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
                        isShuffled = isShuffled,
                        eventReceiver = viewModel
                    )
                    is CardsLoadError -> CardsLoadError(viewModel)
                }
            }
        }
    }
}