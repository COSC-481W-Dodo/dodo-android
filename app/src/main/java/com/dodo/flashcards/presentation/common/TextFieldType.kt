package com.dodo.flashcards.presentation.common

import androidx.annotation.StringRes
import androidx.compose.ui.text.input.KeyboardType
import com.dodo.flashcards.R

enum class TextFieldType(
    val keyboardType: KeyboardType,
    @StringRes val labelResourceId: Int,
    @StringRes val placeholderResourceId: Int? = null,
) {
    EMAIL(
        keyboardType = KeyboardType.Email,
        labelResourceId = R.string.general_email_label,
        placeholderResourceId = R.string.general_placeholder_email_text
    ),
    USERNAME(
        keyboardType = KeyboardType.Text,
        labelResourceId = R.string.general_username_label,
    );
}