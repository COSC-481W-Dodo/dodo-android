package com.dodo.flashcards.presentation.register_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.dodo.flashcards.R
import com.dodo.flashcards.architecture.Router
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.register_screen.RegisterScreenViewEvent.*

@Composable
fun RegisterScreen(
    viewModel: RegisterScreenViewModel,
) {
    viewModel.viewState.collectAsState().value?.apply {
        // Todo, clean up UI
        Column {
            TextField(
                value = textEmail,
                onValueChange = {
                    viewModel.onEvent(TextEmailChanged(changedTo = it))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )
            TextField(
                value = textPass,
                onValueChange = {
                    viewModel.onEvent(TextPassChanged(changedTo = it))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                )
            )
            Button(
                onClick = {
                    viewModel.onEvent(ClickedRegister)
                }
            ) {
                Text(text = stringResource(R.string.register_register_button))
            }
        }
    }
}