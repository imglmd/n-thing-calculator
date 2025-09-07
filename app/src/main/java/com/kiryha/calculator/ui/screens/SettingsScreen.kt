package com.kiryha.calculator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kiryha.calculator.ui.components.SettingsRadioButton
import com.kiryha.calculator.ui.theme.ThemeMode
import com.kiryha.calculator.utils.PreferencesManager

@Composable
fun SettingsScreen(
    onThemeChanged: (ThemeMode) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .statusBarsPadding(),
) {
    val context = LocalContext.current
    val currentTheme = PreferencesManager.getThemeMode(context)

    Column(modifier = modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = { navController.popBackStack() },
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
        SettingsRadioButton(
            options = listOf(ThemeMode.Light, ThemeMode.System, ThemeMode.Dark),
            selectedOption = currentTheme,
            onOptionSelected = { newTheme ->
                onThemeChanged(newTheme)
            },
            optionToString = { theme ->
                when (theme) {
                    ThemeMode.Light -> "Светлая"
                    ThemeMode.System -> "Системная"
                    ThemeMode.Dark -> "Темная"
                }
            },
            label = "Тема"
        )
    }
}