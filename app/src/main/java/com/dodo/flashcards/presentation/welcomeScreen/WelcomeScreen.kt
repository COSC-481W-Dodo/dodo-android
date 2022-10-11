package com.dodo.flashcards.presentation.welcomeScreen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun WelcomeScreen(viewModel: WelcomeScreenViewModel) {
    // Todo remove placeholder
    Text("Welcome placeholder")

    viewModel.viewState.collectAsState().value?.apply {

    }
}