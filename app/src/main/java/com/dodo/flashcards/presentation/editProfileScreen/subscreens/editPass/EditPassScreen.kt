package com.dodo.flashcards.presentation.editProfileScreen.subscreens.editPass

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
import androidx.compose.ui.text.input.KeyboardType
import com.dodo.flashcards.presentation.editProfileScreen.subscreens.editPass.EditPassViewEvent.*

@Composable
fun EditPassScreen(viewModel: EditPassViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        viewModel.viewState.collectAsState().value?.apply {
            Text("Edit Pass")
            Text("For security, enter your old password")
            TextField(
                value = textPassOld,
                onValueChange = {
                    viewModel.onEvent(TextPassOldChanged(it))
                }
            )
            Text("Enter a new password")
            Text("A password must be at least 6 characters")
            TextField(
                value = textPassNew,
                onValueChange = {
                    viewModel.onEvent(TextPassNewChanged(it))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                )
            )
            if (hasSuccessfullySet) {
                Text("Successful update")
            }
            Button(
                enabled = confirmButtonEnabled,
                onClick = {
                    viewModel.onEventDebounced(ClickedConfirm)
                }) {
                Text("Confirm")
            }
            Button(onClick = {
                viewModel.onEventDebounced(ClickedReturn)
            }) {
                Text("Return")
            }
        }
    }
}