package com.dodo.flashcards.presentation.welcomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.R
import com.dodo.flashcards.presentation.theme.DarkColors
import com.dodo.flashcards.presentation.welcomeScreen.WelcomeScreenViewEvent.*

@Composable
fun WelcomeScreen(viewModel: WelcomeScreenViewModel) {
    viewModel.viewState.collectAsState().value?.apply {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primary)
                .drawBehind {
                    val path = Path()
                    path.moveTo(0f, size.height * 0.05f)
                    path.lineTo(size.width, 0.2f * size.height)
                    path.lineTo(size.width, 0.95f * size.height)
                    path.lineTo(0f, 0.8f * size.height)

                    drawPath(
                        path = path,
                        color = DarkColors.background
                    )
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier.fillMaxSize(0.7f),
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 6.dp

            ) {
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

                }
            }
            Button(onClick = {
                viewModel.onEventDebounced(ClickedEditProfile)
            }) {
                Text(text = "Edit Profile")
            }
        }
    }
}