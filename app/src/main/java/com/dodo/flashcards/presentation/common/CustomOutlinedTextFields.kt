package com.dodo.flashcards.presentation.registerScreen.composables

import android.media.Image
import android.text.BoringLayout
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.constraintlayout.widget.Placeholder
import com.dodo.flashcards.R
import com.dodo.flashcards.architecture.EventReceiver
import com.dodo.flashcards.presentation.registerScreen.RegisterScreenViewEvent

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholderText: String = String(),
    keyboardType: KeyboardType,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        label = {
            Text(label)
        },
        placeholder = {
            Text(placeholderText)
        },
    )
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholderText: String = String(),
    keyboardType: KeyboardType,
    onIconChanged: () -> Unit,
    isHidden: Boolean,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        label = {
            Text(label)
        },
        placeholder = {
            Text(placeholderText)
        },
        trailingIcon = {
            IconButton(onClick = onIconChanged) {
                Icon(
                    imageVector = if (isHidden) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                    contentDescription = stringResource(R.string.password_visibility_description)
                )
            }
        })
}
