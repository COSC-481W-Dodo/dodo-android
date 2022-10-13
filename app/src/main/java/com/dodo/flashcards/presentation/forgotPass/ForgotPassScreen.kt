package com.dodo.flashcards.presentation.forgotPass

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.dodo.flashcards.R
import com.dodo.flashcards.presentation.forgotPass.ForgotPassViewEvent.*
import com.dodo.flashcards.presentation.forgotPass.ForgotPassViewState.PendingConfirmation
import com.dodo.flashcards.presentation.forgotPass.ForgotPassViewState.InputEmail
import com.dodo.flashcards.presentation.forgotPass.ForgotPassViewState.InvalidEmail

@Composable
fun ForgotPassScreen(viewModel: ForgotPassViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        viewModel.viewState.collectAsState().value?.apply {
            when (this) {
                is InputEmail -> {
                    Text(stringResource(id = R.string.forgot_pass_prompt))
                    TextField(
                        value = textEmail,
                        onValueChange = {
                            viewModel.onEvent(TextChangedEmail(it))
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        )
                    )
                    Button(onClick = { viewModel.onEventDebounced(ClickedConfirmEmail) }) {
                        Text(stringResource(id = R.string.forgot_pass_email_button))
                    }
                }
                is InvalidEmail -> {
                    Text(stringResource(id = R.string.forgot_pass_invalid_email, textEmail))
                    Button(onClick = { viewModel.onEventDebounced(ClickedReturn) }) {
                        Text(stringResource(R.string.forgot_pass_return_button))
                    }
                }
                is PendingConfirmation -> {
                    Text(stringResource(id = R.string.forgot_pass_confirmation, textEmail))
                    Button(onClick = { viewModel.onEventDebounced(ClickedReturn) }) {
                        Text(stringResource(R.string.forgot_pass_return_button))
                    }
                }
            }
        }
    }
}