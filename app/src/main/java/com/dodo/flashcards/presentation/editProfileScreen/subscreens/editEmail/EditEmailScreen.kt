package com.dodo.flashcards.presentation.editProfileScreen.subscreens.editEmail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.R
import com.dodo.flashcards.presentation.common.CustomOutlinedTextField
import com.dodo.flashcards.presentation.common.PasswordTextField
import com.dodo.flashcards.presentation.common.ScreenBackground
import com.dodo.flashcards.presentation.common.TextFieldType
import com.dodo.flashcards.presentation.editProfileScreen.subscreens.editEmail.EditEmailViewEvent.*
import com.dodo.flashcards.presentation.editProfileScreen.subscreens.editPass.EditPassViewEvent
import com.dodo.flashcards.presentation.registerScreen.RegisterScreenViewEvent
import com.dodo.flashcards.presentation.theme.Typography

@Composable
fun EditEmailScreen(
    viewModel: EditEmailViewModel
) {
    ScreenBackground {
        viewModel.viewState.collectAsState().value?.apply {
            if (hasSuccessfullySet) {
                Text(
                    text = "Changed E-mail",
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
                    text = "Your e-mail has been successfully changed to $textEmail."
                )
                Button(onClick = {
                    viewModel.onEventDebounced(ClickedReturn)
                }) {
                    Text(stringResource(R.string.forgot_pass_return_button))
                }
            } else {
                Text(
                    text = "Edit E-mail",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = Typography.h6,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                PasswordTextField(
                    errorMessage = errorPassMessage,
                    value = textPass,
                    onValueChange = {
                        viewModel.onEvent(
                            TextPassChanged(it)
                        )
                    },
                    label = "Password",
                    keyboardType = KeyboardType.Password,
                    onIconChanged = {
                        viewModel.onEvent(ClickedShowPassword)
                    },
                    passHidden = passHidden
                )
                Text(
                    modifier =
                    Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(0.9f),
                    style = Typography.subtitle2,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                    text = "For your security, enter your password."
                )
                CustomOutlinedTextField(
                    errorMessage = errorEmailMessage,
                    textFieldType = TextFieldType.EMAIL,
                    value = textEmail,
                    onValueChange = { viewModel.onEvent(TextEmailChanged(it)) }
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