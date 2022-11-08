package com.dodo.flashcards.presentation.common.commonModifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*

fun Modifier.angledAccentDrawBehind(
    accentColor: Color,
    backgroundColor: Color
): Modifier = drawBehind {
    val topPaint = Paint().apply {
        asFrameworkPaint().apply {
            shader = LinearGradientShader(
                from = Offset(0f, 0f),
                to = Offset(0f, 0.1f * size.height),
                colors = listOf(accentColor, backgroundColor),
                tileMode = TileMode.Clamp
            )
        }
    }
    val topPath = Path().apply {
        moveTo(0f, 0.05f * size.height)
        lineTo(size.width, 0.15f * size.height)
        lineTo(size.width, 0f)
        lineTo(0f, 0f)
    }
    val bottomPaint = Paint().apply {
        asFrameworkPaint().apply {
            shader = LinearGradientShader(
                from = Offset(0f, size.height),
                to = Offset(0f, 0.9f * size.height),
                colors = listOf(accentColor, backgroundColor),
                tileMode = TileMode.Clamp
            )
        }
    }
    val bottomPath = Path().apply {
        moveTo(size.width, 0.95f * size.height)
        lineTo(0f, 0.85f * size.height)
        lineTo(0f, size.height)
        lineTo(size.width, size.height)
    }
    drawContext.canvas.drawPath(
        path = topPath,
        paint = topPaint
    )

/*
    drawPath(
        path = topPath,
        color = accentColor
    )
*/

    drawContext.canvas.drawPath(
        path = bottomPath,
        paint = bottomPaint
    )
/*
    drawPath(
        path = bottomPath,
        color = accentColor,
    )
*/
}
