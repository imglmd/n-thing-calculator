package com.kiryha.calculator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kiryha.calculator.ui.components.SettingsRadioButtons
import com.kiryha.calculator.ui.theme.FontStyle
import com.kiryha.calculator.ui.theme.NDot57
import com.kiryha.calculator.ui.theme.NType87
import com.kiryha.calculator.ui.theme.ThemeMode
import com.kiryha.calculator.utils.PreferencesManager
import com.kiryha.calculator.utils.WindowInfo
import com.kiryha.calculator.utils.rememberWindowInfo

@Composable
fun SettingsScreen(
    onThemeChanged: (ThemeMode) -> Unit,
    onFontChanged: (FontStyle) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .statusBarsPadding()
) {
    val context = LocalContext.current
    val currentTheme = PreferencesManager.getThemeMode(context)
    val currentFont = PreferencesManager.getFontStyle(context)
    val windowInfo = rememberWindowInfo()
    if(windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
        Column(modifier = modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = "Settings",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 36.sp
                )
            }
            LazyColumn(){
                item {
                    SettingsRadioButtons(
                        options = listOf(ThemeMode.Light, ThemeMode.System, ThemeMode.Dark),
                        selectedOption = currentTheme,
                        onOptionSelected = { newTheme ->
                            println("Theme selected: $newTheme") // Для отладки
                            PreferencesManager.saveThemeMode(context, newTheme)
                            onThemeChanged(newTheme)
                        },
                        optionToTextStyle = { theme ->
                            when (theme) {
                                ThemeMode.Light -> "Светлая" to null
                                ThemeMode.System -> "Системная" to null
                                ThemeMode.Dark -> "Темная" to null
                            }
                        },
                        label = "Тема"
                    )
                }
                item {
                    SettingsRadioButtons(
                        options = listOf(FontStyle.NDot57, FontStyle.NType87),
                        selectedOption = currentFont,
                        onOptionSelected = { newFont ->
                            println("Font selected: $newFont") // Для отладки
                            PreferencesManager.saveFontStyle(context, newFont)
                            onFontChanged(newFont)
                        },
                        optionToTextStyle = { font ->
                            when (font) {
                                FontStyle.NDot57 -> "N-Dot 57" to TextStyle(
                                    fontFamily = NDot57,
                                    fontSize = 18.sp
                                )

                                FontStyle.NType87 -> "N-Type 87" to TextStyle(
                                    fontFamily = NType87,
                                    fontSize = 18.sp
                                )
                            }
                        },
                        label = "Шрифт"
                    )
                }
            }
        }
    } else {
        Column(modifier = modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = "Settings",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 36.sp
                )
            }
            Row {
                SettingsRadioButtons(
                    options = listOf(ThemeMode.Light, ThemeMode.System, ThemeMode.Dark),
                    selectedOption = currentTheme,
                    onOptionSelected = { newTheme ->
                        println("Theme selected: $newTheme") // Для отладки
                        PreferencesManager.saveThemeMode(context, newTheme)
                        onThemeChanged(newTheme)
                    },
                    optionToTextStyle = { theme ->
                        when (theme) {
                            ThemeMode.Light -> "Светлая" to null
                            ThemeMode.System -> "Системная" to null
                            ThemeMode.Dark -> "Темная" to null
                        }
                    },
                    modifier = Modifier.weight(1f),
                    label = "Тема"
                )
                Spacer(modifier = Modifier.size(20.dp))
                SettingsRadioButtons(
                    options = listOf(FontStyle.NDot57, FontStyle.NType87),
                    selectedOption = currentFont,
                    onOptionSelected = { newFont ->
                        println("Font selected: $newFont") // Для отладки
                        PreferencesManager.saveFontStyle(context, newFont)
                        onFontChanged(newFont)
                    },
                    optionToTextStyle = { font ->
                        when (font) {
                            FontStyle.NDot57 -> "N-Dot 57" to TextStyle(
                                fontFamily = NDot57,
                                fontSize = 18.sp
                            )

                            FontStyle.NType87 -> "N-Type 87" to TextStyle(
                                fontFamily = NType87,
                                fontSize = 18.sp
                            )
                        }
                    },
                    modifier = Modifier.weight(1f),
                    label = "Шрифт"
                )

            }
        }

    }
}