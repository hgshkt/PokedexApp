package com.hgshkt.pokedex.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.hgshkt.pokedex.R
import com.hgshkt.pokedex.data.model.UiSimplePokemon
import com.hgshkt.pokedex.ui.listDetail.PokemonSaver

@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    onItemClick: (PokemonSaver) -> Unit
) {
    val pokemons: LazyPagingItems<UiSimplePokemon> =
        viewModel.pokemonsState.collectAsLazyPagingItems()

    if(pokemons.itemCount == 0) {
        if (
            pokemons.loadState.append is LoadState.Error
            || pokemons.loadState.refresh is LoadState.Error
            || pokemons.loadState.prepend is LoadState.Error
        ) {
            ErrorBox()
        } else {
            LoadingBox()
        }
    }

    else {
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
}

@Composable
fun ErrorBox() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Something went wrong...", fontSize = 25.sp)
            Image(
                painter = painterResource(R.drawable.error_image),
                modifier = Modifier.size(100.dp),
                contentDescription = "sad pikachu"
            )
        }
    }
}

@Composable
private fun LoadingBox() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
