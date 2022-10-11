package com.dodo.flashcards.presentation.loginScreen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
fun LoginScreen(viewModel: LoginScreenViewModel) {
    // Todo remove placeholder
    Text("Login placeholder")

    viewModel.viewState.collectAsState().value?.apply {

    }
}