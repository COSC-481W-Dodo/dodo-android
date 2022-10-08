package com.dodo.flashcards.presentation.login_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun LoginScreen(viewModel: LoginScreenViewModel) {
    viewModel.viewState.collectAsState().value?.apply {

    }
}