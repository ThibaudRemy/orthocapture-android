package com.thibaudremy.orthocapture.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF006D77),
    secondary = Color(0xFF3D5A80),
    tertiary = Color(0xFFEE6C4D),
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF83C5BE),
    secondary = Color(0xFF98C1D9),
    tertiary = Color(0xFFFFB199),
)

@Composable
fun OrthoCaptureTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        content = content,
    )
}
