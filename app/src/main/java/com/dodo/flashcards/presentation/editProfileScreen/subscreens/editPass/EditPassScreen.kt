package com.dodo.flashcards.presentation.editProfileScreen.subscreens.editPass

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.R
import com.dodo.flashcards.presentation.common.PasswordTextField
import com.dodo.flashcards.presentation.common.ScreenBackground
import com.dodo.flashcards.presentation.editProfileScreen.subscreens.editPass.EditPassViewEvent.*
import com.dodo.flashcards.presentation.forgotPassScreen.ForgotPassViewEvent
import com.dodo.flashcards.presentation.registerScreen.RegisterScreenViewEvent
import com.dodo.flashcards.presentation.theme.Typography

@Composable
fun EditPassScreen(viewModel: EditPassViewModel) {
    ScreenBackground {
        viewModel.viewState.collectAsState().value?.apply {
            if (hasSuccessfullySet) {
                Text(
                    text = "Changed Password",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = Typography.h6,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier =
                    Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(0.9f),
                    style = Typography.subtitle2,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                    text = "Your password has been successfully changed."
                )
                Button(onClick = {
                    viewModel.onEventDebounced(ClickedReturn)
                }) {
                    Text(stringResource(R.string.forgot_pass_return_button))
                }
            } else {
                Text(
                    text = "Edit Password",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = Typography.h6,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                PasswordTextField(
                    value = textPassOld,
                    onValueChange = {
                        viewModel.onEvent(
                            TextPassOldChanged(it)
                        )
                    },
                    label = "Old Password",
                    keyboardType = KeyboardType.Password,
                    onIconChanged = {
                        viewModel.onEvent(ClickedShowPasswordOld)
                    },
                    passHidden = passHiddenOld
                )
                Text(
                    modifier =
                    Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(0.9f),
                    style = Typography.subtitle2,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                    text = "For your security, enter your previous password."
                )
                PasswordTextField(
                    value = textPassNew,
                    onValueChange = {
                        viewModel.onEvent(
                            TextPassNewChanged(it)
                        )
                    },
                    label = "New Password",
                    keyboardType = KeyboardType.Password,
                    onIconChanged = {
                        viewModel.onEvent(ClickedShowPasswordNew)
                    },
                    passHidden = passHiddenNew
                )
                Text(
                    modifier =
                    Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(0.9f),
                    style = Typography.subtitle2,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.register_register_password_subtext)
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
    }
}