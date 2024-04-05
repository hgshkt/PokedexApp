package com.hgshkt.pokedex.ui.custom.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hgshkt.domain.model.Pokemon
import com.hgshkt.pokedex.ui.custom.text.AutoResizedText
import com.hgshkt.pokedex.ui.theme.pokemonCardColors
import com.hgshkt.pokedex.ui.theme.pokemonNameStyle

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