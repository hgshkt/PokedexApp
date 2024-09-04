package com.hgshkt.pokedex.ui.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgshkt.pokedex.ui.custom.ErrorBox
import com.hgshkt.pokedex.ui.custom.ExpandedView
import com.hgshkt.pokedex.ui.custom.LoadingBox
import com.hgshkt.pokedex.ui.data.model.UiSimplePokemon
import com.hgshkt.pokedex.ui.screens.list.filter.FilterButton
import com.hgshkt.pokedex.ui.screens.list.filter.FilterMenu
import com.hgshkt.pokedex.ui.screens.listDetail.PokemonSaver

@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    onItemClick: (PokemonSaver) -> Unit,
    listState: LazyGridState
) {
    val screenState = viewModel.state.collectAsState().value
    val filterMenuState by viewModel.filterMenuState.collectAsState()
    val downloadingState by viewModel.downloadingState.collectAsState()

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
        when (screenState) {
            is State.Loading -> LoadingBox()
            is State.Error -> {
                ErrorBox(screenState.message) {
                    Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                    Button(onClick = { screenState.handle() }) {
                        Text("Ok")
                    }
                }
            }

            is State.Display -> {
                val pokemons = screenState.pokemons

                Column {
                    if (downloadingState.count < downloadingState.target)
                        DownloadingProgressBar(downloadingState)

                    PokemonListSuccess(
                        listState = listState,
                        pokemons = pokemons
                    ) { saver ->
                        onItemClick(saver)
                    }
                }
            }
        }
    }
}

@Composable
fun DownloadingProgressBar(downloadingState: DownloadingState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(downloadingState.status)
        Text("${downloadingState.count} / ${downloadingState.target}")
    }
}

@Composable
fun PokemonListSuccess(
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