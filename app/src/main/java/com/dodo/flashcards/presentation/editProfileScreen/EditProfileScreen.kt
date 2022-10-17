package com.dodo.flashcards.presentation.editProfileScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.R
import com.dodo.flashcards.presentation.common.ScreenBackground
import com.dodo.flashcards.presentation.editProfileScreen.EditProfileViewEvent.*
import com.dodo.flashcards.presentation.editProfileScreen.subscreens.editUsername.EditUsernameViewEvent
import com.dodo.flashcards.presentation.forgotPassScreen.ForgotPassViewEvent
import com.dodo.flashcards.presentation.loginScreen.LoginScreenViewEvent
import com.dodo.flashcards.presentation.theme.Typography

@Composable
fun EditProfileScreen(viewModel: EditProfileViewModel) {
    ScreenBackground {
        viewModel.viewState.collectAsState().value?.apply {
            Text(
                text = "Edit Profile",
                modifier = Modifier.padding(horizontal = 16.dp),
                style = Typography.h6,
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Column {
                TextButton(
                    modifier = Modifier.defaultMinSize(
                        minWidth = dimensionResource(id = R.dimen.min_width_button)
                    ),
                    onClick = {
                        viewModel.onEventDebounced(ClickedEditEmail)
                    }) {
                    Text(text = "Edit E-mail")
                }
                TextButton(
                    modifier = Modifier.defaultMinSize(
                        minWidth = dimensionResource(id = R.dimen.min_width_button)
                    ),
                    onClick = {
                        viewModel.onEventDebounced(ClickedEditUsername)
                    }) {
                    Text(text = "Edit Username")
                }
                TextButton(
                    modifier = Modifier.defaultMinSize(
                        minWidth = dimensionResource(id = R.dimen.min_width_button)
                    ),
                    onClick = {
                        viewModel.onEventDebounced(ClickedEditPassword)
                    }) {
                    Text(text = "Edit Password")
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