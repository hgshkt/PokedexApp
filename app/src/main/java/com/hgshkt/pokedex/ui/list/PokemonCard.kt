package com.hgshkt.pokedex.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
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
                    modifier = Modifier
                        .padding(4.dp)
                        .padding(bottom = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .fillMaxSize(),
                        model = pokemon.imageUrl,
                        contentDescription = "Pokemon image",
                        loading = {
                            Box(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .aspectRatio(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.widthIn(0.dp, 30.dp)
                                )
                            }
                        },
                        error = {
                            Box(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .aspectRatio(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.widthIn(0.dp, 30.dp)
                                )
                            }
                        }
                    )
                    Text("№${pokemon.id}")
                    AutoResizedText(pokemon.name.uppercase(), style = pokemonNameStyle)
                }
            }
        }
    }
}