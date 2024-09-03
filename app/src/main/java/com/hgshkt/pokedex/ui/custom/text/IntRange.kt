package com.hgshkt.pokedex.ui.custom.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NamedIntRange(
    text: String,
    modifier: Modifier = Modifier,
    startValue: String = "0",
    endValue: String = "0",
    onStartValueChange: (String) -> Unit,
    onEndValueChange: (String) -> Unit,
    placeholderStart: String,
    placeholderEnd: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text)
        IntRange(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            startValue = startValue,
            endValue = endValue,
            onStartValueChange = onStartValueChange,
            onEndValueChange = onEndValueChange,
            placeholderStart = placeholderStart,
            placeholderEnd = placeholderEnd
        )
    }
}

@Composable
fun IntRange(
    modifier: Modifier = Modifier,
    startValue: String = "0",
    endValue: String = "0",
    onStartValueChange: (String) -> Unit,
    onEndValueChange: (String) -> Unit,
    placeholderStart: String,
    placeholderEnd: String
) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DigitTextField(
            Modifier.weight(1f),
            startValue,
            onValueChange = onStartValueChange,
            placeholder = placeholderStart
        )
        Text(
            text = "-",
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = 4.dp),
            fontSize = 22.sp
        )
        DigitTextField(
            Modifier.weight(1f),
            endValue,
            onValueChange = onEndValueChange,
            placeholder = placeholderEnd
        )
    }
}