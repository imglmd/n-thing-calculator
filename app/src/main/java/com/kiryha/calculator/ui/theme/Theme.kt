package com.kiryha.calculator.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    background = Black,
    primary = DarkGray,
    onPrimary = White,
    secondary = MediumGray,
    onSecondary = White,
    tertiary = Red,
    onTertiary = White,
    onBackground = White

)

private val LightColorScheme = lightColorScheme(
    background = Gray,
    primary = White,
    onPrimary = Black,
    secondary = LightGray,
    onSecondary = Black,
    tertiary = Red,
    onBackground = Black

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun CalculatorTheme(
    themeMode: ThemeMode = ThemeMode.System,
    fontStyle: FontStyle = FontStyle.NDot57,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val darkTheme = when (themeMode){
        ThemeMode.Light -> false
        ThemeMode.Dark -> true
        ThemeMode.System -> isSystemInDarkTheme()
    }

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val fontFamily = when (fontStyle) {
        FontStyle.NDot57 -> NDot57
        FontStyle.NType87 -> NType87
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = getTypography(fontFamily),
        content = content
    )
}

enum class ThemeMode {
    Light, Dark, System
}
enum class FontStyle {
    NDot57, NType87
}