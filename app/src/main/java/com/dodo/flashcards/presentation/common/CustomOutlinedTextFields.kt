package com.dodo.flashcards.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.R
import com.dodo.flashcards.presentation.theme.Typography

@Composable
fun CustomOutlinedTextField(
    textFieldType: TextFieldType,
    value: String,
    onValueChange: (String) -> Unit,
    errorMessage: String? = null,
    enabled: Boolean = true,
    ) {
    OutlinedTextField(
        enabled = enabled,
        isError = errorMessage != null,
        modifier = Modifier.fillMaxWidth(0.9f),
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = textFieldType.keyboardType
        ),
        label = {
            Text(stringResource(textFieldType.labelResourceId))
        },
        placeholder = {
            textFieldType.placeholderResourceId?.apply {
                Text(stringResource(this))
            }
        },
    )
    errorMessage?.apply {
        Text(
            modifier =
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(0.9f),
            style = Typography.subtitle2,
            color = Color(139, 0, 0),
            textAlign = TextAlign.Center,
            text = this
        )
    }
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
    errorMessage: String? = null,
    enabled: Boolean = true,
    ) {
    OutlinedTextField(
        enabled = enabled,
        isError = errorMessage != null,
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
    errorMessage?.apply {
        Text(
            modifier =
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(0.9f),
            style = Typography.subtitle2,
            color = Color(139, 0, 0),
            textAlign = TextAlign.Center,
            text = this
        )
    }
}
