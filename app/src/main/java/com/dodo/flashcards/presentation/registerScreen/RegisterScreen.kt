package com.dodo.flashcards.presentation.registerScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.dodo.flashcards.R
import com.dodo.flashcards.presentation.registerScreen.RegisterScreenViewEvent.*
import com.dodo.flashcards.presentation.common.CustomOutlinedTextField
import com.dodo.flashcards.presentation.common.PasswordTextField
import com.dodo.flashcards.presentation.theme.DarkColors


@Composable
fun RegisterScreen(viewModel: RegisterScreenViewModel) {
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
            CustomOutlinedTextField(
                value = textUsername,
                onValueChange = {
                    viewModel.onEvent(TextUsernameChanged(changedTo = it))
                },
                label = stringResource(id = R.string.general_username_label),
                keyboardType = KeyboardType.Text
            )
            CustomOutlinedTextField(
                value = textEmail,
                onValueChange = {
                    viewModel.onEvent(TextEmailChanged(changedTo = it))
                },
                label = stringResource(R.string.general_email_label),
                placeholderText = stringResource(R.string.general_placeholder_email_text),
                keyboardType = KeyboardType.Email
            )
            PasswordTextField(
                value = textPass,
                onValueChange = {
                    viewModel.onEvent(TextPassChanged(changedTo = it))
                },
                label = stringResource(R.string.general_password_label),
                keyboardType = KeyboardType.Password,
                onIconChanged = {
                    viewModel.onEvent(ClickedShowPassword)
                },
                passHidden = hidePassword
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