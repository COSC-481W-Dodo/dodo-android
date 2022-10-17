package com.dodo.flashcards.presentation.loginScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.R
import com.dodo.flashcards.presentation.common.ScreenBackground
import com.dodo.flashcards.presentation.loginScreen.LoginScreenViewEvent.*
import com.dodo.flashcards.presentation.common.CustomOutlinedTextField
import com.dodo.flashcards.presentation.common.PasswordTextField
import com.dodo.flashcards.presentation.common.TextFieldType
import com.dodo.flashcards.presentation.theme.Typography

@Composable
fun LoginScreen(viewModel: LoginScreenViewModel) {
    ScreenBackground {
        viewModel.viewState.collectAsState().value?.apply {
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = Typography.h6,
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.app_name_subtext),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = Typography.subtitle1,
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Italic
            )
            CustomOutlinedTextField(
                enabled = buttonsEnabled,
                errorMessage = errorEmailMessage,
                textFieldType = TextFieldType.EMAIL,
                value = textEmail,
                onValueChange = { viewModel.onEvent(TextEmailChanged(it)) },
            )
            PasswordTextField(
                enabled = buttonsEnabled,
                errorMessage = errorPasswordMessage,
                value = textPass,
                onValueChange = { viewModel.onEvent(TextPassChanged(it)) },
                label = stringResource(R.string.general_password_label),
                keyboardType = KeyboardType.Password,
                onIconChanged = {
                    viewModel.onEvent(ClickedShowPassword)
                },
                passHidden = passHidden,
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    modifier = Modifier.defaultMinSize(
                        minWidth = dimensionResource(id = R.dimen.min_width_button)
                    ),
                    enabled = buttonsEnabled,
                    onClick = {
                        viewModel.onEventDebounced(ClickedLogin)
                    }
                ) {
                    Text(text = stringResource(R.string.login_login_button))
                }
                OutlinedButton(
                    modifier = Modifier.defaultMinSize(
                        minWidth = dimensionResource(id = R.dimen.min_width_button)
                    ),
                    enabled = buttonsEnabled,
                    onClick = {
                        viewModel.onEventDebounced(ClickedRegister)
                    }) {
                    Text(text = stringResource(R.string.login_register_button))
                }
                TextButton(
                    modifier = Modifier.defaultMinSize(
                        minWidth = dimensionResource(id = R.dimen.min_width_button)
                    ),
                    enabled = buttonsEnabled,
                    onClick = {
                        viewModel.onEventDebounced(ClickedForgotPassword)
                    }) {
                    Text(text = stringResource(R.string.login_forgot_password_button))
                }
            }
        }
    }
}