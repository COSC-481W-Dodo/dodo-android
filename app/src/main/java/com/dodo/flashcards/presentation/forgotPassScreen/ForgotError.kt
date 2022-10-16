package com.dodo.flashcards.presentation.forgotPassScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
fun ForgotError(
    email: String,
    eventReceiver: EventReceiver<ForgotPassViewEvent>
) {
    val placeholderStrIndex = stringResource(R.string.forgot_pass_invalid_email).indexOf("%s")
    Text(
        text = stringResource(id = R.string.forgot_pass_error_header),
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
        text = AnnotatedString(
            text = stringResource(R.string.forgot_pass_invalid_email, email),
            spanStyles = listOf(
                AnnotatedString.Range(
                    SpanStyle(fontWeight = FontWeight.Bold),
                    placeholderStrIndex,
                    placeholderStrIndex + email.length
                )
            )
        )
    )
    Button(onClick = { eventReceiver.onEventDebounced(ForgotPassViewEvent.ClickedReturn) }) {
        Text(stringResource(R.string.forgot_pass_return_button))
    }

}