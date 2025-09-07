package com.kiryha.calculator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kiryha.calculator.ui.theme.FontStyle
import com.kiryha.calculator.utils.PreferencesManager

@Composable
fun CalculatorButton(
    symbol: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    fontSize: TextUnit = 49.sp,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val currentFont = PreferencesManager.getFontStyle(context)

    val (startPadding, topPadding) = when (currentFont) {
        FontStyle.NDot57 -> (fontSize.value / 7).dp to (fontSize.value / 7).dp
        FontStyle.NType87 -> 0.dp to (fontSize.value / 5).dp
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(CircleShape)
            .clickable { onClick() }
            .background(color = color)
    ) {
        Text(
            text = symbol,
            modifier = Modifier.padding(start = startPadding, top = topPadding),
            fontSize = fontSize,
            textAlign = TextAlign.Center,
            color = textColor
        )
    }
}