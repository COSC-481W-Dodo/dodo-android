package com.dodo.flashcards.presentation.forgotPassScreen

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.R
import com.dodo.flashcards.architecture.EventReceiver
import com.dodo.flashcards.presentation.common.CustomOutlinedTextField
import com.dodo.flashcards.presentation.common.TextFieldType
import com.dodo.flashcards.presentation.theme.Typography

@Composable
fun ForgotIdle(
    isError: Boolean,
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
        errorMessage = if (isError) {
            "This is not a valid registered e-mail."
        } else {
            null
        },
        textFieldType = TextFieldType.EMAIL,
        value = textEmail,
        onValueChange = {
            eventReceiver.onEvent(ForgotPassViewEvent.TextChangedEmail(it))
        },
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