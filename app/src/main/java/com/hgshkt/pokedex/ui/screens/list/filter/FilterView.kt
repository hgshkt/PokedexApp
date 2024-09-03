package com.hgshkt.pokedex.ui.screens.list.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hgshkt.pokedex.ui.custom.text.NamedIntRange

@Composable
fun FilterMenu(
    menuState: FilterMenuState,
    weightStartValueChange: (String) -> Unit,
    weightEndValueChange: (String) -> Unit,
    heightStartValueChange: (String) -> Unit,
    heightEndValueChange: (String) -> Unit,
    onTypeClick: (FilterMenuState.SelectedType) -> Unit
) {

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        TypesChoosingMenu(menuState.selectedTypes) {
            onTypeClick(it)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            NamedIntRange(
                modifier = Modifier.weight(1f),
                text = "Weight:",
                startValue = menuState.weightStart,
                onStartValueChange = weightStartValueChange,
                endValue = menuState.weightEnd,
                onEndValueChange = weightEndValueChange,
                placeholderStart = menuState.MIN_WEIGHT.toString(),
                placeholderEnd = menuState.MAX_WEIGHT.toString()
            )
            NamedIntRange(
                modifier = Modifier.weight(1f),
                text = "Height:",
                startValue = menuState.heightStart,
                onStartValueChange = heightStartValueChange,
                endValue = menuState.heightEnd,
                onEndValueChange = heightEndValueChange,
                placeholderStart = menuState.MIN_HEIGHT.toString(),
                placeholderEnd = menuState.MAX_HEIGHT.toString()
            )
        }
    }
}

@Composable
fun TypesChoosingMenu(
    types: List<FilterMenuState.SelectedType>,
    modifier: Modifier = Modifier,
    click: (FilterMenuState.SelectedType) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(types) { type ->
            PokemonTypeSelectingButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        click(type)
                    },
                settingsType = type
            )
        }
    }
}

@Composable
fun PokemonTypeSelectingButton(
    settingsType: FilterMenuState.SelectedType,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 14.sp
) {
    Box(modifier) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    if (settingsType.selected) settingsType.type.backgroundColor
                    else Color(0xFF929292)
                )
                .padding(vertical = 5.dp)
        ) {
            Text(
                text = settingsType.type.text,
                fontSize = fontSize,
                fontWeight = FontWeight.Bold,
                color = settingsType.type.textColor
            )
        }
    }
}

@Composable
fun FilterButton(
    placeholder: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    onOpenButtonClick: () -> Unit,
    onSearchButtonClick: () -> Unit,
    text: String,
    onTextValueChange: (String) -> Unit
) {
    val icon =
        if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(
            onClick = onOpenButtonClick
        ) {
            Icon(
                painter = rememberVectorPainter(image = icon),
                contentDescription = "Filter Button"
            )
        }
        TextField(
            value = text,
            placeholder = placeholder,
            onValueChange = onTextValueChange,
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = onSearchButtonClick
        ) {
            Icon(
                painter = rememberVectorPainter(image = Icons.Default.Search),
                contentDescription = "Filter Button"
            )
        }
    }
}