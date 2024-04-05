package com.hgshkt.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.hgshkt.domain.model.Pokemon
import com.hgshkt.pokedex.ui.custom.text.AutoResizedText
import com.hgshkt.pokedex.ui.list.ListViewModel
import com.hgshkt.pokedex.ui.listDetail.MainScreen
import com.hgshkt.pokedex.ui.listDetail.PokemonSaver
import com.hgshkt.pokedex.ui.theme.PokedexTheme
import com.hgshkt.pokedex.ui.theme.pokemonCardBackgroundColor
import com.hgshkt.pokedex.ui.theme.pokemonCardColors
import com.hgshkt.pokedex.ui.theme.pokemonNameStyle
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3WindowSizeClassApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    onItemClick: (PokemonSaver) -> Unit
) {
    val pokemons: LazyPagingItems<Pokemon> =
        viewModel.pokemons.collectAsLazyPagingItems()

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

@Composable
fun PokemonCard(
    pokemon: Pokemon?,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {
    pokemon?.let {
        Card(
            modifier = modifier
                .padding(8.dp)
                .clickable { onItemClick() },
            shape = RoundedCornerShape(8.dp),
            colors = pokemonCardColors
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier.padding(4.dp).padding(bottom = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    AsyncImage(model = pokemon.imageUrl, contentDescription = "Pokemon image")
                    Text("№${pokemon.id}")
                    AutoResizedText(pokemon.name.uppercase(), style = pokemonNameStyle)
                }
            }
        }
    }
}