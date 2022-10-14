package com.dodo.flashcards.presentation.editProfileScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dodo.flashcards.presentation.editProfileScreen.EditProfileViewEvent.*

@Composable
fun EditProfileScreen(viewModel: EditProfileViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        viewModel.viewState.collectAsState().value?.apply {
            Text(text = "Edit Profile")
            TextButton(onClick = { viewModel.onEventDebounced(ClickedEditUsername) }) {
                Text("Edit Username")
            }
            TextButton(onClick = { viewModel.onEventDebounced(ClickedEditPassword) }) {
                Text("Edit Password")
            }
            Button(onClick = { viewModel.onEventDebounced(ClickedReturn) }) {
                Text("Return")
            }
        }
    }
}