package com.dodo.flashcards.presentation.loginScreen

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.dodo.flashcards.R
import com.dodo.flashcards.presentation.loginScreen.LoginScreenViewEvent.*
import com.dodo.flashcards.presentation.registerScreen.composables.CustomOutlinedTextField
import com.dodo.flashcards.presentation.registerScreen.composables.PasswordTextField
import com.dodo.flashcards.presentation.theme.DarkColors

@Composable
fun LoginScreen(viewModel: LoginScreenViewModel) {
    viewModel.viewState.collectAsState().value?.apply {
        // Todo, clean up UI
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primary)
                .drawBehind {
                    val path = Path()
                    path.moveTo(0f, size.height * 0.05f)
                    path.lineTo(size.width, 0.2f * size.height)
                    path.lineTo(size.width, 0.95f * size.height)
                    path.lineTo(0f, 0.8f * size.height)

                    drawPath(
                        path = path,
                        color = DarkColors.background
                    )
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(id = R.string.app_name))
            CustomOutlinedTextField(
                value = textEmail,
                onValueChange = { viewModel.onEvent(TextEmailChanged(it)) },
                label = stringResource(R.string.general_email_label),
                keyboardType = KeyboardType.Email,
            )
            PasswordTextField(
                value = textPass,
                onValueChange = { viewModel.onEvent(TextPassChanged(it)) },
                label = stringResource(R.string.general_password_label),
                keyboardType = KeyboardType.Password,
                onIconChanged = {
                    viewModel.onEvent(ClickedShowPassword)
                },
                isHidden = isHidden,
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