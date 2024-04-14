package com.hgshkt.pokedex.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.hgshkt.domain.model.Pokemon
import com.hgshkt.pokedex.ui.custom.cards.PokemonCard
import com.hgshkt.pokedex.ui.listDetail.PokemonSaver

@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    onItemClick: (PokemonSaver) -> Unit
) {
    val pokemons: LazyPagingItems<Pokemon> =
        viewModel.pokemonsState.collectAsLazyPagingItems()

    LazyVerticalGrid(
        modifier = Modifier.wrapContentWidth(),
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