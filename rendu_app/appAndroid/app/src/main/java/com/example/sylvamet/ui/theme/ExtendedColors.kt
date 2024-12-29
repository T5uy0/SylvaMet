package com.example.sylvamet.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color

data class ExtendedColors(
    val primaryTitle: Color,
    val backgroundSearch: Color
)

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        primaryTitle = Color.Unspecified,
        backgroundSearch = Color.White
    )
}

val MaterialTheme.extendedColors: ExtendedColors
    @Composable
    @ReadOnlyComposable
    get() = LocalExtendedColors.current
