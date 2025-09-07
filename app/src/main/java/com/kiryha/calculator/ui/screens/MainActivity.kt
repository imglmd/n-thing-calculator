package com.kiryha.calculator.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kiryha.calculator.ui.calculator.Calculator
import com.kiryha.calculator.ui.calculator.CalculatorViewModel
import com.kiryha.calculator.ui.theme.CalculatorTheme
import com.kiryha.calculator.ui.theme.ThemeMode
import com.kiryha.calculator.utils.PreferencesManager
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var currentTheme by remember {
                mutableStateOf(PreferencesManager.getThemeMode(this))
            }
            CalculatorTheme(themeMode = currentTheme) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = MainScreen
                ) {
                    composable<MainScreen>(
                        enterTransition = { slideInHorizontally(animationSpec = tween(300)) { 0 } },
                        exitTransition = { slideOutHorizontally(animationSpec = tween(300)) { 0 } },
                        popEnterTransition = { slideInHorizontally(animationSpec = tween(300)) { 0 } },
                        popExitTransition = { slideOutHorizontally(animationSpec = tween(300)) { 0 } }
                    ) {
                        val viewModel = viewModel<CalculatorViewModel>()
                        val state = viewModel.state
                        val buttonSpacing = 4.dp
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            Calculator(
                                state = state,
                                onAction = viewModel::onAction,
                                buttonSpacing = buttonSpacing,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(MaterialTheme.colorScheme.background)
                                    .padding(8.dp)
                                    .statusBarsPadding(),
                                navController = navController
                            )
                        }
                    }
                    composable<SettingsScreen>(
                        enterTransition = { slideInHorizontally(animationSpec = tween(300)) { it } },
                        exitTransition = { slideOutHorizontally(animationSpec = tween(300)) { -it } },
                        popEnterTransition = { slideInHorizontally(animationSpec = tween(300)) { -it } },
                        popExitTransition = { slideOutHorizontally(animationSpec = tween(300)) { it } }
                    ) {
                        SettingsScreen(
                            onThemeChanged = { newTheme ->
                                currentTheme = newTheme
                                PreferencesManager.saveThemeMode(this@MainActivity, newTheme)
                            },
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}

@Serializable
object MainScreen

@Serializable
object SettingsScreen