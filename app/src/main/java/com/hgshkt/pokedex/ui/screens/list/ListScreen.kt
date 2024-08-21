package com.hgshkt.pokedex.ui.screens.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.hgshkt.pokedex.ui.custom.ErrorBox
import com.hgshkt.pokedex.ui.custom.LoadingBox
import com.hgshkt.pokedex.ui.data.model.UiSimplePokemon
import com.hgshkt.pokedex.ui.data.model.UiType
import com.hgshkt.pokedex.ui.screens.listDetail.PokemonSaver

@Preview
@Composable
fun ExpandedViewPreview(modifier: Modifier = Modifier) {
    var isExpanded by remember { mutableStateOf(false) }
    ExpandedView(
        hiddenPart = {
            FilterMenu()
        },
        visiblePart = {
            FilterButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                placeholder = { Text("Enter Pokemon name") },
                isExpanded = isExpanded,
                onClick = {
                    isExpanded = !isExpanded
                },
                onSearchButtonClick = { text ->
                    // viewModel.search(text)
                }
            )
        },
        expanded = isExpanded
    )
}

@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    onItemClick: (PokemonSaver) -> Unit
) {
    val pokemons: LazyPagingItems<UiSimplePokemon> =
        viewModel.pokemonsState.collectAsLazyPagingItems()

    if (pokemons.itemCount == 0) {
        if (
            pokemons.loadState.append is LoadState.Error
            || pokemons.loadState.refresh is LoadState.Error
            || pokemons.loadState.prepend is LoadState.Error
        ) {
            ErrorBox()
        } else {
            LoadingBox()
        }
    } else {
        CompleteListScreen(
            modifier = Modifier.wrapContentWidth(),
            pokemons = pokemons,
            onItemClick = onItemClick
        )
    }
}

@Composable
fun CompleteListScreen(
    modifier: Modifier = Modifier,
    pokemons: LazyPagingItems<UiSimplePokemon>,
    onItemClick: (PokemonSaver) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    Column(modifier) {
        ExpandedView(
            hiddenPart = {
                FilterMenu()
            },
            visiblePart = {
                FilterButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    placeholder = { Text("Enter Pokemon name") },
                    isExpanded = isExpanded,
                    onClick = {
                        isExpanded = !isExpanded
                    },
                    onSearchButtonClick = { text ->
                        // viewModel.search(text)
                    }
                )
            },
            expanded = isExpanded
        )
        CompleteList(
            pokemons = pokemons
        ) { saver ->
            onItemClick(saver)
        }
    }
}

@Composable
fun FilterMenu() {
    var weightStartValue by remember { mutableStateOf("0") }
    var weightEndValue by remember { mutableStateOf("10000") }
    var heightStartValue by remember { mutableStateOf("0") }
    var heightEndValue by remember { mutableStateOf("10000") }

    val types = remember { UiType.entries }

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        TypesChoosingMenu(types)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            NamedIntRange(
                modifier = Modifier.weight(1f),
                text = "Weight:",
                startValue = weightStartValue,
                onStartValueChange = {
                    weightStartValue = it
                },
                endValue= weightEndValue,
                onEndValueChange = {
                    weightEndValue = it
                }
            )
            NamedIntRange(
                modifier = Modifier.weight(1f),
                text = "Height:",
                startValue = heightStartValue,
                onStartValueChange = {
                    heightStartValue = it
                },
                endValue= heightEndValue,
                onEndValueChange = {
                    heightEndValue = it
                }
            )
        }

    }
}

@Composable
fun TypesChoosingMenu(
    types: List<UiType>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(types) { type ->
            PokemonType(
                type = type
            )
        }
    }
}

@Composable
private fun PokemonType(type: UiType) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(type.backgroundColor)
            .padding(horizontal = 22.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = type.text,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = type.textColor
        )
    }
}

@Composable
fun NamedIntRange(
    text: String,
    modifier: Modifier = Modifier,
    startValue: String = "0",
    endValue: String = "0",
    onStartValueChange: (String) -> Unit,
    onEndValueChange: (String) -> Unit
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
            onEndValueChange = onEndValueChange
        )
    }
}

@Composable
fun IntRange(
    modifier: Modifier = Modifier,
    startValue: String = "0",
    endValue: String = "0",
    onStartValueChange: (String) -> Unit,
    onEndValueChange: (String) -> Unit
) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier.weight(1f),
            value = startValue,
            onValueChange = {
                onStartValueChange(it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(
            text = "-",
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = 4.dp),
            fontSize = 22.sp
        )
        TextField(
            modifier = Modifier.weight(1f),
            value = endValue,
            onValueChange = {
                onEndValueChange(it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
fun FilterButton(
    placeholder: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    onClick: () -> Unit,
    onSearchButtonClick: (String) -> Unit
) {
    val icon = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
    var text by remember { mutableStateOf("") }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(
            onClick = {
                onClick()
            }
        ) {
            Icon(
                painter = rememberVectorPainter(image = icon),
                contentDescription = "Filter Button"
            )
        }
        TextField(
            value = text,
            placeholder = placeholder,
            onValueChange = { text = it },
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = {
                onSearchButtonClick(text)
            }
        ) {
            Icon(
                painter = rememberVectorPainter(image = Icons.Default.Search),
                contentDescription = "Filter Button"
            )
        }
    }
}

@Composable
private fun CompleteList(
    modifier: Modifier = Modifier,
    pokemons: LazyPagingItems<UiSimplePokemon>,
    onItemClick: (PokemonSaver) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        items(
            count = pokemons.itemCount,
            key = pokemons.itemKey { it.id },
            contentType = pokemons.itemContentType { "pokemons" }
        ) { index ->
            val pokemon = pokemons[index]
            PokemonCard(
                pokemon = pokemon,
            ) { onItemClick(PokemonSaver(pokemon!!)) }
        }
    }
}

@Composable
fun ExpandedView(
    modifier: Modifier = Modifier,
    hiddenPart: @Composable () -> Unit,
    visiblePart: @Composable (Modifier) -> Unit,
    expanded: Boolean
) {
    Column(modifier) {
        AnimatedVisibility(
            modifier = modifier,
            visible = expanded,
            enter = expandVertically(tween(600, easing = EaseInOut)),
            exit = shrinkVertically(tween(600, easing = EaseOut))
        ) {
            hiddenPart()
        }
        visiblePart(modifier)
    }
}