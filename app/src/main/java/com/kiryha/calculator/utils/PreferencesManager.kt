package com.kiryha.calculator.utils

import android.content.Context
import androidx.core.content.edit
import com.kiryha.calculator.ui.theme.FontStyle
import com.kiryha.calculator.ui.theme.ThemeMode

object PreferencesManager {
    private const val PREFS_NAME = "CalculatorPrefs"
    private const val KEY_THEME_MODE = "theme_mode"
    private const val KEY_FONT_STYLE = "font_style"

    fun saveThemeMode(context: Context, themeMode: ThemeMode) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit {
            putString(KEY_THEME_MODE, themeMode.name)
        }
    }

    fun getThemeMode(context: Context): ThemeMode {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val themeName = prefs.getString(KEY_THEME_MODE, ThemeMode.System.name)
        return try {
            ThemeMode.valueOf(themeName ?: ThemeMode.System.name)
        } catch (e: IllegalArgumentException) {
            ThemeMode.System
        }
    }

    fun saveFontStyle(context: Context, fontStyle: FontStyle) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit {
            putString(KEY_FONT_STYLE, fontStyle.name)
        }
    }

    fun getFontStyle(context: Context): FontStyle {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val fontName = prefs.getString(KEY_FONT_STYLE, FontStyle.NDot57.name)
        return try {
            FontStyle.valueOf(fontName ?: FontStyle.NDot57.name)
        } catch (e: IllegalArgumentException) {
            FontStyle.NDot57
        }
    }
}