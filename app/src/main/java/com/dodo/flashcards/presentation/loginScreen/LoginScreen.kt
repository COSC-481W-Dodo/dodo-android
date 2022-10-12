package com.dodo.flashcards.presentation.loginScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.dodo.flashcards.R
import com.dodo.flashcards.presentation.loginScreen.LoginScreenViewEvent.*

@Composable
fun LoginScreen(viewModel: LoginScreenViewModel) {
    viewModel.viewState.collectAsState().value?.apply {
        // Todo, clean up UI
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(id = R.string.app_name))
            TextField(
                value = textEmail,
                onValueChange = {
                    viewModel.onEvent(TextEmailChanged(it))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )
            TextField(
                value = textPass,
                onValueChange = {
                    viewModel.onEvent(TextPassChanged(it))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                )
            )
            Button(
                enabled = buttonsEnabled,
                onClick = {
                    viewModel.onEventDebounced(ClickedLogin)
                }
            ) {
                Text(text = stringResource(R.string.login_login_button))
            }
            OutlinedButton(
                enabled = buttonsEnabled,
                onClick = {
                    viewModel.onEventDebounced(ClickedRegister)
                }) {
                Text(text = stringResource(R.string.login_register_button))
            }
            TextButton(
                enabled = buttonsEnabled,
                onClick = {
                    viewModel.onEventDebounced(ClickedForgotPassword)
                }) {
                Text(text = stringResource(R.string.login_forgot_password_button))
            }
        }
    }
}