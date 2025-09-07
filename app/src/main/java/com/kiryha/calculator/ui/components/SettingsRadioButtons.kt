package com.kiryha.calculator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun <T> SettingsRadioButtons(
    options: List<T>,
    selectedOption: T,
    onOptionSelected: (T) -> Unit,
    optionToTextStyle: (T) -> Pair<String, TextStyle?>, // Изменено: возвращает пару (строка, стиль)
    label: String = "Выберите опцию:",
    modifier: Modifier = Modifier
) {
    var selected by remember { mutableStateOf(selectedOption) }

    Column(
        modifier = modifier.padding(top = 20.dp)
    ) {
        Text(
            text = label,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(10.dp)
        )
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp)),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            options.forEach { option ->
                val (text, textStyle) = optionToTextStyle(option)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(4.dp))
                        .clickable {
                            selected = option
                            onOptionSelected(option)
                        }
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = text,
                        style = textStyle ?: MaterialTheme.typography.bodyMedium, // Используем стиль или дефолтный
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    RadioButton(
                        selected = (selected == option),
                        onClick = {
                            selected = option
                            onOptionSelected(option)
                        },
                        colors = RadioButtonDefaults.colors(
                            unselectedColor = MaterialTheme.colorScheme.secondary,
                            selectedColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            }
        }
    }
}