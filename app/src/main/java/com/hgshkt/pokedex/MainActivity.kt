package com.hgshkt.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.hgshkt.domain.model.Pokemon
import com.hgshkt.pokedex.ui.list.ListViewModel
import com.hgshkt.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

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
                    ListScreen()
                }
            }
        }
    }

    @Composable
    fun ListScreen(
        viewModel: ListViewModel = hiltViewModel()
    ) {
        val pokemons: LazyPagingItems<Pokemon> =
            viewModel.pokemons.collectAsLazyPagingItems()

        LazyColumn {
            items(
                count = pokemons.itemCount,
                key = pokemons.itemKey { it.id },
                contentType = pokemons.itemContentType { "pokemons" }
            ) { index ->
                val pokemon = pokemons[index]
                PokemonCard(pokemon)
            }
        }
    }

    @Composable
    fun PokemonCard(pokemon: Pokemon?) {
        pokemon?.let {
            Card {
                Column {
                    Text("№${pokemon.id}")
                    Text(pokemon.name.uppercase(Locale.ROOT))
                }
            }
        }
    }
}