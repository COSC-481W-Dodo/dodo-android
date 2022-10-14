package com.dodo.flashcards.presentation.registerScreen

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
import com.dodo.flashcards.presentation.registerScreen.RegisterScreenViewEvent.*

@Composable
fun RegisterScreen(viewModel: RegisterScreenViewModel) {
    viewModel.viewState.collectAsState().value?.apply {
        // Todo, clean up UI
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("USERNAME") // Todo, remove this with polish
            TextField(
                value = textUsername,
                onValueChange = {
                    viewModel.onEvent(TextUsernameChanged(changedTo = it))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )
            Text("EMAIL") // Todo, remove this with polish
            TextField(
                value = textEmail,
                onValueChange = {
                    viewModel.onEvent(TextEmailChanged(changedTo = it))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )
            Text("PASSWORD") // Todo, remove this with polish
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
                enabled = buttonsEnabled,
                onClick = {
                    viewModel.onEventDebounced(ClickedRegister)
                }
            ) {
                Text(text = stringResource(R.string.register_register_button))
            }
        }
    }
}