package com.hgshkt.pokedex.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgshkt.pokedex.BuildConfig
import com.hgshkt.pokedex.ui.custom.ErrorBox
import com.hgshkt.pokedex.ui.custom.LoadingBox
import com.hgshkt.pokedex.ui.custom.image.PokemonImage
import com.hgshkt.pokedex.ui.custom.text.AutoResizedText
import com.hgshkt.pokedex.ui.data.model.UiPokemon
import com.hgshkt.pokedex.ui.data.model.UiPokemonAbility
import com.hgshkt.pokedex.ui.data.model.UiStats
import com.hgshkt.pokedex.ui.data.model.UiType

private val pokemonNameStyle = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Bold)

@Preview
@Composable
fun DetailScreenPreview() {
    if (BuildConfig.DEBUG) {
        val pokemon = UiPokemon(
            123, "Pokemon Name",
            "https://i.pinimg.com/550x/cb/33/49/cb3349b86ca661ca61ae9a36d88d70d4.jpg",
            types = listOf(UiType.NORMAL, UiType.ICE),
            stats = UiStats(
                12, 54, 23, 61, 14, 63
            ),
            height = 100,
            weight = 99,
            abilities = listOf(
                UiPokemonAbility(12, "MyAbility", "Effect details"),
                UiPokemonAbility(
                    14,
                    "MyAb ility2",
                    "Effect details qwredfa sfafadsfwqfda fadsfqwq"
                ),
                UiPokemonAbility(
                    15,
                    "MyAbi lity123",
                    "Effect details qwerfdq qi bq ipqubfh qqdfuq "
                )
            )
        )
        with(pokemon) {

        }
    }
}

@Composable
fun DetailScreen(
    id: Int,
    viewModel: DetailViewModel = hiltViewModel()
) {
    when (val state = viewModel.state.collectAsState().value) {
        is DetailViewModel.State.Loading ->
            LoadingBox()

        is DetailViewModel.State.Error ->
            ErrorBox(state.message)

        is DetailViewModel.State.Success -> with(state.pokemon) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PokemonImage(
                    url = imageUrl,
                    contentDescription = "pokemon image",
                    modifier = Modifier
                        .widthIn(0.dp, 400.dp)
                        .fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "№$id", fontSize = 20.sp)
                    AutoResizedText(text = name, style = pokemonNameStyle)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        types.forEach { type ->
                            PokemonType(type)
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    AbilityList(
                        modifier = Modifier
                            .fillMaxWidth(),
                        abilities = abilities
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    StatsLayer(
                        stats = stats,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        val fontSize = 24.sp
                        Text("Height: $height", fontSize = fontSize)
                        Text("Weight: $weight", fontSize = fontSize)
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = "Fetch pokemon") {
        viewModel.loadPokemon(id)
    }
}

@Composable
private fun PokemonType(type: UiType, modifier: Modifier = Modifier) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(type.backgroundColor)
                .padding(horizontal = 22.dp, vertical = 12.dp)
        ) {
            Text(
                text = type.text,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = type.textColor
            )
        }
    }
}

@Composable
fun StatsLayer(
    stats: UiStats,
    modifier: Modifier = Modifier,
    textFontSize: TextUnit = 26.sp,
    contentPadding: PaddingValues = PaddingValues(vertical = 12.dp, horizontal = 20.dp),
    nameFontWeight: FontWeight = FontWeight.Bold,
) {
    with(stats) {
        ElevatedCard(
            modifier,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {
            Column(modifier = Modifier.padding(contentPadding)) {
                Text("HP: $hp", fontSize = textFontSize, fontWeight = nameFontWeight)
                Text("ATK: $attack", fontSize = textFontSize, fontWeight = nameFontWeight)
                Text("DEF: $defense", fontSize = textFontSize, fontWeight = nameFontWeight)
                Text("SPA: $specialAttack", fontSize = textFontSize, fontWeight = nameFontWeight)
                Text("SPD: $specialDefense", fontSize = textFontSize, fontWeight = nameFontWeight)
                Text("SPD: $speed", fontSize = textFontSize, fontWeight = nameFontWeight)
                Spacer(modifier = Modifier.height(12.dp))
                Text("Total: $total", fontSize = textFontSize, fontWeight = nameFontWeight)
            }
        }
    }
}

@Composable
fun AbilityList(modifier: Modifier, abilities: List<UiPokemonAbility>) {
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp)
        ) {
            Text(text = "Abilities:", fontSize = 26.sp)
            if (abilities.isNotEmpty()) {
                abilities.forEach { ability ->
                    Ability(
                        ability = ability,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            } else {
                CircularProgressIndicator(modifier = Modifier.padding(10.dp))
            }
        }
    }
}

@Composable
fun Ability(
    modifier: Modifier = Modifier,
    ability: UiPokemonAbility,
    nameFontSize: TextUnit = 26.sp,
    nameFontWeight: FontWeight = FontWeight.Bold,
    textFontSize: TextUnit = 22.sp,
    contentPadding: Dp = 8.dp
) {
    ElevatedCard(
        modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(Modifier.padding(contentPadding)) {
            Text(ability.name, fontSize = nameFontSize, fontWeight = nameFontWeight)
            Text(ability.effect, fontSize = textFontSize)
        }
    }
}