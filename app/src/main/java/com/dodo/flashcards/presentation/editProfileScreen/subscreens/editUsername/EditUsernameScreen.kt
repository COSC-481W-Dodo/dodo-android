package com.dodo.flashcards.presentation.editProfileScreen.subscreens.editUsername

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.R
import com.dodo.flashcards.presentation.common.CustomOutlinedTextField
import com.dodo.flashcards.presentation.common.ScreenBackground
import com.dodo.flashcards.presentation.common.TextFieldType
import com.dodo.flashcards.presentation.editProfileScreen.EditProfileViewEvent
import com.dodo.flashcards.presentation.editProfileScreen.subscreens.editUsername.EditUsernameViewEvent.*
import com.dodo.flashcards.presentation.forgotPassScreen.ForgotPassViewEvent
import com.dodo.flashcards.presentation.theme.Typography
import com.dodo.flashcards.util.Screen

@Composable
fun EditUsernameScreen(viewModel: EditUsernameViewModel) {
    ScreenBackground {
        viewModel.viewState.collectAsState().value?.apply {
            Text(
                text = "Edit Username",
                modifier = Modifier.padding(horizontal = 16.dp),
                style = Typography.h6,
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            CustomOutlinedTextField(
                textFieldType = TextFieldType.USERNAME,
                value = textUsername,
                onValueChange = {
                    viewModel.onEvent(TextUsernameChanged(it))
                },
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
            if (hasSuccessfullySet) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(0.9f),
                    style = Typography.subtitle2,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center,
                    text = "Successfully updated username."
                )
            }
            Column {
                Button(
                    modifier = Modifier.defaultMinSize(
                        minWidth = dimensionResource(id = R.dimen.min_width_button)
                    ),
                    //   enabled = buttonsEnabled,
                    onClick = {
                        viewModel.onEventDebounced(ClickedConfirm)
                    }
                ) {
                    Text(text = "Confirm")
                }
                TextButton(
                    modifier = Modifier.defaultMinSize(
                        minWidth = dimensionResource(id = R.dimen.min_width_button)
                    ),
                    onClick = {
                        viewModel.onEventDebounced(ClickedReturn)
                    }) {
                    Text("Return")
                }
            }
        }
    }
}