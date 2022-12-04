package com.dodo.flashcards.presentation.welcomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.dodo.flashcards.R
import com.dodo.flashcards.presentation.common.AppScaffold
import com.dodo.flashcards.presentation.common.ScreenBackground
import com.dodo.flashcards.presentation.theme.Typography
import com.dodo.flashcards.presentation.welcomeScreen.WelcomeScreenViewEvent.*

@Composable
fun WelcomeScreen(viewModel: WelcomeViewModel) {
    viewModel.viewState.collectAsState().value?.apply {
        AppScaffold(
            actions = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    IconButton(
                        onClick = { viewModel.onEvent(ClickedMenu) },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = String(),
                        )
                        DropdownMenu(
                            expanded = isMenuOpen,
                            onDismissRequest = { viewModel.onEvent(ClickedMenu) }
                        ) {
                            DropdownMenuItem(onClick = {
                                viewModel.onEventDebounced(
                                    ClickedEditProfile
                                )
                            }) {
                                Text(stringResource(R.string.welcome_edit_profile_button))
                            }
                            DropdownMenuItem(onClick = { viewModel.onEventDebounced(ClickedLogout) }) {
                                Text(stringResource(R.string.welcome_logout_button))
                            }
                        }
                    }
                }
            }
        ) {
            ScreenBackground {
                Text(
                    text = username?.let {
                        stringResource(R.string.welcome_message_username, it)
                    } ?: stringResource(R.string.welcome_error_username),
                    style = Typography.h6,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                        //.animateBackground(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    OutlinedButton(
                        modifier = Modifier.defaultMinSize(
                            minWidth = dimensionResource(id = R.dimen.min_width_button)
                        ),
                        onClick = { viewModel.onEventDebounced(ClickedViewAllTags) },
                    ) {
                        Text("View All Flashcards")
                    }
                    OutlinedButton(
                        modifier = Modifier.defaultMinSize(
                            minWidth = dimensionResource(id = R.dimen.min_width_button)
                        ),
                        onClick = { viewModel.onEventDebounced(ClickedViewMyTags) },
                    ) {
                        Text("View My Flashcards")
                    }
                }

            }

        }

    }
}