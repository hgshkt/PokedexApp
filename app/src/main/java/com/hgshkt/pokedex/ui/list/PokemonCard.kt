package com.hgshkt.pokedex.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hgshkt.pokedex.data.model.UiPokemon
import com.hgshkt.pokedex.data.model.UiSimplePokemon
import com.hgshkt.pokedex.ui.custom.image.PokemonImage
import com.hgshkt.pokedex.ui.custom.text.AutoResizedText
import com.hgshkt.pokedex.ui.theme.pokemonCardColors
import com.hgshkt.pokedex.ui.theme.pokemonNameStyle

@Composable
fun PokemonCard(
    pokemon: UiSimplePokemon?,
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
                    modifier = Modifier
                        .padding(4.dp)
                        .padding(bottom = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    PokemonImage(modifier = Modifier.fillMaxSize(), url = pokemon.imageUrl)
                    Text("№${pokemon.id}")
                    AutoResizedText(pokemon.name.uppercase(), style = pokemonNameStyle)
                }
            }
        }
    }
}