package com.dodo.flashcards.presentation.registerScreen

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.R
import com.dodo.flashcards.architecture.EventReceiver
import com.dodo.flashcards.presentation.common.CustomOutlinedTextField
import com.dodo.flashcards.presentation.common.PasswordTextField
import com.dodo.flashcards.presentation.common.TextFieldType
import com.dodo.flashcards.presentation.registerScreen.RegisterScreenViewEvent.*
import com.dodo.flashcards.presentation.loginScreen.LoginScreenViewEvent
import com.dodo.flashcards.presentation.theme.Typography

@Composable
fun RegisterIdle(
    hidePassword: Boolean,
    textEmail: String,
    textPass: String,
    textUsername: String,
    eventReceiver: EventReceiver<RegisterScreenViewEvent>,
    errorEmailMessage: String? = null,
    errorPasswordMessage: String? = null,
    errorUsernameMessage: String? = null
) {
    Text(
        text = stringResource(id = R.string.register_register_button),
        modifier = Modifier.padding(horizontal = 16.dp),
        style = Typography.h6,
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
    CustomOutlinedTextField(
        errorMessage = errorUsernameMessage,
        textFieldType = TextFieldType.USERNAME,
        value = textUsername,
        onValueChange = { eventReceiver.onEvent(TextUsernameChanged(it)) }
    )
    Text(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(0.9f),
        style = Typography.subtitle2,
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Center,
        text = stringResource(R.string.register_register_username_subtext)
    )
    CustomOutlinedTextField(
        errorMessage = errorEmailMessage,
        textFieldType = TextFieldType.EMAIL,
        value = textEmail,
        onValueChange = { eventReceiver.onEvent(TextEmailChanged(it)) }
    )
    Text(
        modifier =
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(0.9f),
        style = Typography.subtitle2,
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Center,
        text = stringResource(R.string.register_register_email_subtext)
    )
    PasswordTextField(
        errorMessage = errorPasswordMessage,
        value = textPass,
        onValueChange = {
            eventReceiver.onEvent(RegisterScreenViewEvent.TextPassChanged(changedTo = it))
        },
        label = stringResource(R.string.general_password_label),
        keyboardType = KeyboardType.Password,
        onIconChanged = {
            eventReceiver.onEvent(RegisterScreenViewEvent.ClickedShowPassword)
        },
        passHidden = hidePassword
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
    Button(
        modifier = Modifier.defaultMinSize(
            minWidth = dimensionResource(id = R.dimen.min_width_button)
        ),
        //   enabled = buttonsEnabled,
        onClick = {
            eventReceiver.onEventDebounced(RegisterScreenViewEvent.ClickedRegister)
        }
    ) {
        Text(text = stringResource(R.string.register_register_button))
    }
}