package com.dodo.flashcards.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.dodo.flashcards.R

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholderText: String = String(),
    keyboardType: KeyboardType,
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(0.9f),
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
    passHidden: Boolean,
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(0.9f),
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
                    imageVector = if (!passHidden) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                    contentDescription = stringResource(R.string.password_visibility_description)
                )
            }
        },
        visualTransformation = if (!passHidden) VisualTransformation.None else PasswordVisualTransformation()
    )
}
