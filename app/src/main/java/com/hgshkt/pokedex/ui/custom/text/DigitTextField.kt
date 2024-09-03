package com.hgshkt.pokedex.ui.custom.text

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun DigitTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    var isError by remember { mutableStateOf(false) }
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = {
            if (it.isNotEmpty()) {
                if (it.last().isDigit()) {
                    isError = false
                    onValueChange(it)
                } else {
                    isError = true
                }
            } else {
                isError = true
                onValueChange(it)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        placeholder = {
            Text(text = placeholder, color = Color.Gray)
        },
        singleLine = true
    )
}