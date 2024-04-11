package com.hgshkt.pokedex.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hgshkt.domain.model.Ability
import com.hgshkt.domain.model.Pokemon
import com.hgshkt.pokedex.ui.custom.text.AutoResizedText
import com.hgshkt.pokedex.ui.theme.pokemonNameStyle

@Composable
fun DetailScreen(pokemon: Pokemon) {
    Column(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = pokemon.imageUrl,
            contentDescription = "pokemon image",
            modifier = Modifier
                .widthIn(0.dp, 400.dp)
                .fillMaxWidth()
        )
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            PokemonTitle(
                modifier = Modifier
                    .padding(4.dp),
                id = pokemon.id,
                name = pokemon.name
            )
            AbilityList(
                modifier = Modifier.padding(8.dp),
                abilities = pokemon.abilities
            )
            Text("Type:")
            Row {
//                pokemon.types.forEach { type ->
//                    PokemonType(type)
//                }
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text("Stats:")
                Row {
                    Column {
                        Text("HP:")
                        Text("Attack")
                        Text("Defense")
                    }
                    Column {
                        Text("Sp. Atk:")
                        Text("Sp. Def")
                        Text("Speed")
                    }
                }
            }
            Row {
                Text("Height: ")
                Text("Weight: ")
            }

        }
    }
}

@Composable
fun AbilityList(modifier: Modifier, abilities: List<Ability>) {
    Column {
        Text("Abilities:")
        abilities.forEach { ability ->
            Text(ability.name)
            Text(ability.effect)
        }
    }
}

@Composable
fun PokemonTitle(modifier: Modifier = Modifier, id: Int, name: String) {
    Box(
        modifier = modifier
    ) {
        Column {
            Text(text = "#$id")
            AutoResizedText(text = name, style = pokemonNameStyle)
        }
    }
}