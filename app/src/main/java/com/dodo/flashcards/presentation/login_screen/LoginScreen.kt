package com.dodo.flashcards.presentation.login_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.dodo.flashcards.architecture.Router
import com.dodo.flashcards.presentation.MainDestination

@Composable
fun LoginScreen(
    viewModel: LoginScreenViewModel,
) {
    viewModel.viewState.collectAsState().value?.apply {

    }
}