package com.dodo.flashcards.presentation.editProfileScreen.subscreens.editUsername

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dodo.flashcards.presentation.editProfileScreen.subscreens.editUsername.EditUsernameViewEvent.*

@Composable
fun EditUsernameScreen(viewModel: EditUsernameViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        viewModel.viewState.collectAsState().value?.apply {
            Text("Edit Username")
            TextField(
                value = textUsername,
                onValueChange = {
                    viewModel.onEvent(TextUsernameChanged(it))
                }
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