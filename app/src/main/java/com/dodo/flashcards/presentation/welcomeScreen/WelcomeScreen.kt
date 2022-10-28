package com.dodo.flashcards.presentation.welcomeScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.R
import com.dodo.flashcards.presentation.common.ScreenBackground
import com.dodo.flashcards.presentation.theme.Typography
import com.dodo.flashcards.presentation.welcomeScreen.WelcomeScreenViewEvent.*

@Composable
fun WelcomeScreen(viewModel: WelcomeViewModel) {
    ScreenBackground {
        viewModel.viewState.collectAsState().value?.apply {
            Text(
                text = username?.let {
                    stringResource(R.string.welcome_message_username, it)
                } ?: stringResource(R.string.welcome_error_username),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = Typography.h6,
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Column {
                Button(
                    modifier = Modifier.defaultMinSize(
                        minWidth = dimensionResource(id = R.dimen.min_width_button)
                    ),
                    onClick = {
                        viewModel.onEventDebounced(ClickedEditProfile)
                    }) {
                    Text(text = stringResource(R.string.welcome_edit_profile_button))
                }
                OutlinedButton(
                    modifier = Modifier.defaultMinSize(
                        minWidth = dimensionResource(id = R.dimen.min_width_button)
                    ),
                    onClick = {
                        viewModel.onEventDebounced(ClickedViewTags)
                    }) {
                    Text(text = "View Tags")
                }
                OutlinedButton(
                    modifier = Modifier.defaultMinSize(
                        minWidth = dimensionResource(id = R.dimen.min_width_button)
                    ),
                    onClick = {
                        viewModel.onEventDebounced(ClickedLogout)
                    }) {
                    Text(text = stringResource(R.string.welcome_logout_button))
                }

            }
        }
    }
}