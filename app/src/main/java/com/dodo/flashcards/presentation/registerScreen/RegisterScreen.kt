package com.dodo.flashcards.presentation.registerScreen

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.dodo.flashcards.presentation.common.ScreenBackground
import com.dodo.flashcards.presentation.registerScreen.RegisterScreenViewState.*


@Composable
fun RegisterScreen(viewModel: RegisterViewModel) {
    ScreenBackground {
        viewModel.viewState.collectAsState().value?.apply {
            when (this) {
                is Error -> Text("Error") // todo fancy this
                is Idle -> RegisterIdle(
                    hidePassword,
                    textEmail,
                    textPass,
                    textUsername,
                    viewModel,
                    errorEmailMessage,
                    errorPasswordMessage,
                    errorUsernameMessage,
                )
                is Loading -> CircularProgressIndicator()
                is Success -> RegisterSuccess(
                    email,
                    viewModel
                )
            }
        }

    }
}