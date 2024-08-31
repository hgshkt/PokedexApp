package com.hgshkt.pokedex.ui.screens.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgshkt.pokedex.ui.custom.ErrorBox
import com.hgshkt.pokedex.ui.custom.LoadingBox
import com.hgshkt.pokedex.ui.data.model.UiSimplePokemon
import com.hgshkt.pokedex.ui.screens.list.FilterMenuState.Companion.HEIGHT_END_DEFAULT
import com.hgshkt.pokedex.ui.screens.list.FilterMenuState.Companion.HEIGHT_START_DEFAULT
import com.hgshkt.pokedex.ui.screens.list.FilterMenuState.Companion.WEIGHT_END_DEFAULT
import com.hgshkt.pokedex.ui.screens.list.FilterMenuState.Companion.WEIGHT_START_DEFAULT
import com.hgshkt.pokedex.ui.screens.listDetail.PokemonSaver

@Preview
@Composable
fun ExpandedViewPreview(modifier: Modifier = Modifier) {

}

@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    onItemClick: (PokemonSaver) -> Unit,
    listState: LazyGridState
) {
    val screenState by viewModel.state.collectAsState()
    val filterMenuState by viewModel.filterMenuState.collectAsState()

    when (screenState) {
        is ListViewModel.State.Loading -> {
            LoadingBox()
        }

        is ListViewModel.State.Error -> {
            ErrorBox((screenState as ListViewModel.State.Error).message)
        }

        is ListViewModel.State.Loaded -> {
            Column {
                ExpandedView(
                    hiddenPart = {
                        FilterMenu(
                            menuState = filterMenuState,
                            weightStartValueChange = { value ->
                                viewModel.updateFilterWeightStart(value)
                            },
                            weightEndValueChange = { value ->
                                viewModel.updateFilterWeightEnd(value)
                            },
                            heightStartValueChange = { value ->
                                viewModel.updateFilterHeightStart(value)
                            },
                            heightEndValueChange = { value ->
                                viewModel.updateFilterHeightEnd(value)
                            },
                            onTypeClick = { type ->
                                viewModel.updateFilterPokemonType(type)
                            }
                        )
                    },
                    visiblePart = {
                        FilterButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            placeholder = { Text("Enter Pokemon name") },
                            isExpanded = filterMenuState.opened,
                            onOpenButtonClick = {
                                viewModel.openFilterMenu()
                            },
                            onSearchButtonClick = {
                                viewModel.startFilter()
                            },
                            onTextValueChange = { text ->
                                viewModel.updateFilterText(text)
                            },
                            text = filterMenuState.text
                        )
                    },
                    expanded = filterMenuState.opened
                )
                when (screenState as ListViewModel.State.Loaded) {
                    is ListViewModel.State.Loaded.Default -> {
                        val pokemons =
                            (screenState as ListViewModel.State.Loaded.Default).pokemons

                        CompleteList(
                            listState = listState,
                            pokemons = pokemons
                        ) { saver ->
                            onItemClick(saver)
                        }
                    }

                    ListViewModel.State.Loaded.Loading -> {
                        LoadingBox()
                    }
                }
            }
        }
    }
}

@Composable
fun CompleteList(
    pokemons: List<UiSimplePokemon>,
    listState: LazyGridState,
    onPokemonCardClick: (PokemonSaver) -> Unit
) {
    LazyVerticalGrid(
        state = listState,
        columns = GridCells.Fixed(3)
    ) {
        items(pokemons) { pokemon ->
            PokemonCard(
                pokemon = pokemon,
            ) {
                onPokemonCardClick(PokemonSaver(pokemon))
            }
        }
    }
}

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
                placeholderStart = WEIGHT_START_DEFAULT.toString(),
                placeholderEnd = WEIGHT_END_DEFAULT.toString()
            )
            NamedIntRange(
                modifier = Modifier.weight(1f),
                text = "Height:",
                startValue = menuState.heightStart,
                onStartValueChange = heightStartValueChange,
                endValue = menuState.heightEnd,
                onEndValueChange = heightEndValueChange,
                placeholderStart = HEIGHT_START_DEFAULT.toString(),
                placeholderEnd = HEIGHT_END_DEFAULT.toString()
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
        columns = GridCells.Fixed(2),
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
    modifier: Modifier = Modifier
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
                .padding(horizontal = 22.dp, vertical = 12.dp)
        ) {
            Text(
                text = settingsType.type.text,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = settingsType.type.textColor
            )
        }
    }
}

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

@Composable
fun DigitTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    maxLength: Int = 5,
    placeholder: String
) {
    var isError by remember { mutableStateOf(false) }
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = {
            if (it.last().isDigit()) {
                if (it.length == maxLength + 1) {
                    isError = true
                } else {
                    isError = false
                    onValueChange(it)
                }
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        isError = isError,
        placeholder = {
            Text(text = placeholder, color = Color.Gray)
        }
    )
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
    val icon = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown

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