package com.dodo.flashcards.presentation.forgotPassScreen

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import com.dodo.flashcards.presentation.registerScreen.RegisterScreenViewEvent
import com.dodo.flashcards.presentation.theme.Typography

@Composable
fun ForgotIdle(
    textEmail: String,
    eventReceiver: EventReceiver<ForgotPassViewEvent>
) {
    Text(
        text = stringResource(id = R.string.forgot_pass_header),
        modifier = Modifier.padding(horizontal = 16.dp),
        style = Typography.h6,
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
    Text(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(0.9f),
        style = Typography.subtitle2,
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Center,
        text = stringResource(id = R.string.forgot_pass_prompt)
    )
    CustomOutlinedTextField(
        value = textEmail,
        onValueChange = {
            eventReceiver.onEvent(ForgotPassViewEvent.TextChangedEmail(it))
        },
        label = stringResource(id = R.string.general_email_label),
        keyboardType = KeyboardType.Text
    )
    Button(
        modifier = Modifier.defaultMinSize(
            minWidth = dimensionResource(id = R.dimen.min_width_button)
        ),
        //   enabled = buttonsEnabled,
        onClick = {
            eventReceiver.onEventDebounced(ForgotPassViewEvent.ClickedConfirmEmail)
        }
    ) {
        Text(text = stringResource(id = R.string.forgot_pass_email_button))
    }
}