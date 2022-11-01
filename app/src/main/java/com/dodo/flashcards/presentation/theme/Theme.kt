package com.dodo.flashcards.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = DarkColors

private val LightColorPalette = lightColors(
    primary = Color(0xFFE7F2F8),
    primaryVariant = Color(0, 47, 108),
    secondary = Color(206, 147, 216),
    background = Color(245, 245, 245),
    surface = Color(227, 227, 223),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color(46, 46, 46),
    onSurface = Color.Black,
)
private val CustomPalette = lightColors(
    primary = Color(0xFF006781),
    onPrimary = Color(0xFFffffff),
    primaryVariant = Color(0xFFb9eaff),
    secondary = Color(0xFF4c626b),
    onSecondary = Color(0xFFFFFFFF),
    secondaryVariant = Color(0xFFcfe6f1),
    surface = Color(0xFFfbfcfe),
    onSurface = Color(0xff191c1d),
    background = Color(0xFFfbfcfe),
    onBackground = Color(0xFF191c1d),
)

@Composable
fun FlashcardsAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = CustomPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}