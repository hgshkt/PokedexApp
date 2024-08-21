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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.hgshkt.pokedex.ui.custom.ErrorBox
import com.hgshkt.pokedex.ui.custom.LoadingBox
import com.hgshkt.pokedex.ui.data.model.UiSimplePokemon
import com.hgshkt.pokedex.ui.screens.listDetail.PokemonSaver

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
    modifier: Modifier,
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
                FilterButton(modifier, isExpanded) {
                    isExpanded = !isExpanded
                }
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
    Column(
        modifier = Modifier
            .background(Color.Red)
            .height(300.dp)
            .fillMaxWidth()
    ) {
        Text("Menu")
    }
}

@Composable
fun FilterButton(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    onClick: () -> Unit
) {
    var icon = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
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