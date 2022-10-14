package com.dodo.flashcards.presentation.welcomeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.dodo.flashcards.R
import com.dodo.flashcards.presentation.welcomeScreen.WelcomeScreenViewEvent.*

@Composable
fun WelcomeScreen(viewModel: WelcomeScreenViewModel) {
    viewModel.viewState.collectAsState().value?.apply {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = username?.let {
                    stringResource(R.string.welcome_message_username, it)
                } ?: stringResource(R.string.welcome_error_username)
            )
            Button(onClick = {
                viewModel.onEventDebounced(ClickedLogout)
            }) {
                Text(text = stringResource(R.string.welcome_logout_button))
            }
            Button(onClick = {
                viewModel.onEventDebounced(ClickedEditProfile)
            }) {
                Text(text = "Edit Profile")
            }
        }
    }
}