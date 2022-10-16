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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.R
import com.dodo.flashcards.architecture.EventReceiver
import com.dodo.flashcards.presentation.theme.Typography

@Composable
fun RegisterSuccess(
    email: String,
    eventReceiver: EventReceiver<RegisterScreenViewEvent>
) {
    Text(
        text = stringResource(id = R.string.register_email_verification_header_1),
        modifier = Modifier.padding(horizontal = 16.dp),
        style = Typography.h6,
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
    val placeholderStrIndex = stringResource(R.string.register_email_verification_prompt_1).indexOf("%s")
    Text(
        modifier =
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(0.9f),
        style = Typography.subtitle2,
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Center,
        text = AnnotatedString(
            text = stringResource(R.string.register_email_verification_prompt_1, email),
            spanStyles = listOf(
                AnnotatedString.Range(
                    SpanStyle(fontWeight = FontWeight.Bold),
                    placeholderStrIndex,
                    placeholderStrIndex + email.length
                )
            )
        )
    )
    Text(
        modifier =
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(0.9f),
        style = Typography.subtitle2,
        color = MaterialTheme.colors.onBackground,
        textAlign = TextAlign.Center,
        text = stringResource(R.string.register_email_verification_prompt_2)
    )
    Button(
        modifier = Modifier.defaultMinSize(
            minWidth = dimensionResource(id = R.dimen.min_width_button)
        ),
        //   enabled = buttonsEnabled,
        onClick = {
            eventReceiver.onEventDebounced(RegisterScreenViewEvent.ClickedAcknowledgeVerification)
        }
    ) {
        Text(text = stringResource(R.string.register_continue_button))
    }
}