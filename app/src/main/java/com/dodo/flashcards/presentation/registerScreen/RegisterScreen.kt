package com.dodo.flashcards.presentation.registerScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.R
import com.dodo.flashcards.presentation.registerScreen.RegisterScreenViewEvent.*
import com.dodo.flashcards.presentation.common.CustomOutlinedTextField
import com.dodo.flashcards.presentation.common.PasswordTextField
import com.dodo.flashcards.presentation.common.ScreenBackground
import com.dodo.flashcards.presentation.registerScreen.RegisterScreenViewState.*
import com.dodo.flashcards.presentation.theme.DarkColors
import com.dodo.flashcards.presentation.theme.Typography


@Composable
fun RegisterScreen(viewModel: RegisterScreenViewModel) {
    ScreenBackground {
        viewModel.viewState.collectAsState().value?.apply {
            when (this) {
                is Error -> Text("Error") // todo fancy this
                is Idle -> RegisterIdle(
                    hidePassword,
                    textEmail,
                    textPass,
                    textUsername,
                    viewModel
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