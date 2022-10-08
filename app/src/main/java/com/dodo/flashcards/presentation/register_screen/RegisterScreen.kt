package com.dodo.flashcards.presentation.register_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun RegisterScreen(viewModel: RegisterScreenViewModel) {
    viewModel.viewState.collectAsState().value?.apply {

    }
}