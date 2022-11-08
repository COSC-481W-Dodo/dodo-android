package com.dodo.flashcards.presentation.viewCardsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dodo.flashcards.presentation.common.ScreenBackground
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewState.*
import com.dodo.flashcards.presentation.viewCardsScreen.subscreen.CardsLoaded


@Composable
fun ViewCardsScreen(viewModel: ViewCardsViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        viewModel.viewState.collectAsState().value?.apply {
            when (this) {
                is CardsLoading -> CircularProgressIndicator()
                is CardsLoaded -> CardsLoaded(
                    currentCard,
                    nextCard,
                    isFlipped,
                    viewModel
                )
                is CardsLoadError -> { // todo
                }
            }
        }
    }
}