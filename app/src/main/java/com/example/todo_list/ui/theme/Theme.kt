package com.example.todo_list.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    secondary = Secondary,
    onSecondary = OnSecondary,
    background = PageBackground,
    onBackground = Color.Black,
    surface = CardBackground,
    onSurface = Color.Black,
    surfaceVariant = TitleBackground,
    onSurfaceVariant = Color.White,
    surfaceTint = CardBackground,
    error = Error,
    onError = OnError,
)

@Composable
fun ToDoListTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}