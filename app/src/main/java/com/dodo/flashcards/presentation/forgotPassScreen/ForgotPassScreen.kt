package com.dodo.flashcards.presentation.forgotPassScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.R
import com.dodo.flashcards.presentation.common.ScreenBackground
import com.dodo.flashcards.presentation.forgotPassScreen.ForgotPassViewEvent.*
import com.dodo.flashcards.presentation.forgotPassScreen.ForgotPassViewState.*
import com.dodo.flashcards.presentation.theme.Typography

@Composable
fun ForgotPassScreen(viewModel: ForgotPassViewModel) {
    ScreenBackground {
        viewModel.viewState.collectAsState().value?.apply {
            when (this) {
                is InputEmail -> ForgotIdle(
                    textEmail = textEmail,
                    eventReceiver = viewModel
                )
                is InvalidEmail -> ForgotError(
                    email = textEmail,
                    eventReceiver = viewModel
                )
                is Loading -> CircularProgressIndicator()
                is PendingConfirmation -> ForgotSuccess(textEmail, viewModel)
            }
        }
    }
}
