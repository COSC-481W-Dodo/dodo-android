package com.dodo.flashcards.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = DarkColors

private val LightColorPalette = lightColors(
    primary = Color(1, 87, 155),
    primaryVariant = Color(0, 47, 108),
    secondary = Color(206, 147, 216),
    background = Color(239, 239, 236),
    surface = Color(227, 227, 223),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color(46, 46, 46),
    onSurface = Color.Black,
)

@Composable
fun FlashcardsAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors =  LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}