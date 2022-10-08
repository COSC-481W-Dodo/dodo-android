package com.dodo.flashcards.presentation.welcome_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun WelcomeScreen(viewModel: WelcomeScreenViewModel) {
    viewModel.viewState.collectAsState().value?.apply {

    }
}