package com.hgshkt.pokedex.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hgshkt.pokedex.ui.custom.ErrorBox
import com.hgshkt.pokedex.ui.custom.LoadingBox
import com.hgshkt.pokedex.ui.custom.image.PokemonImage
import com.hgshkt.pokedex.ui.custom.text.AutoResizedText
import com.hgshkt.pokedex.ui.data.model.UiPokemonAbility
import com.hgshkt.pokedex.ui.data.model.UiStats
import com.hgshkt.pokedex.ui.data.model.UiType

private val idStyle = TextStyle(fontSize = 20.sp)
private val pokemonNameStyle = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Bold)

private val abilityBackground = Color(0xFFD3F0FF)
private val statsBorder = Color(0xFFD3F0FF)

@Composable
fun DetailScreen(
    id: Int,
    viewModel: DetailViewModel = hiltViewModel()
) {
    when (val state = viewModel.state.collectAsState().value) {
        is DetailViewModel.DetailViewModelState.Loading ->
            LoadingBox()

        is DetailViewModel.DetailViewModelState.Error ->
            ErrorBox(state.message)

        is DetailViewModel.DetailViewModelState.Success -> with(state.pokemon) {
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
                        .padding(24.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "№$id", style = idStyle)
                        AutoResizedText(text = name, style = pokemonNameStyle)
                    }
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
                    Spacer(modifier = Modifier.height(8.dp))
                    StatsLayer(stats)
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
private fun PokemonType(type: UiType) {
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

@Composable
fun Border(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(4.dp, statsBorder, shape = RoundedCornerShape(16.dp))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}

@Composable
fun StatsLayer(stats: UiStats) {
    Border {
        val statSize = 26.sp
        with(stats) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                Column {
                    Text("HP: $hp", fontSize = statSize)
                    Text("Attack: $attack", fontSize = statSize)
                    Text("Defense: $defense", fontSize = statSize)
                }
                Spacer(modifier = Modifier.width(20.dp))
                Column {
                    Text("Sp. Atk: $specialAttack", fontSize = statSize)
                    Text("Sp. Def: $specialDefense", fontSize = statSize)
                    Text("Speed: $speed", fontSize = statSize)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text("Total: $total", fontSize = statSize)
        }
    }
}

@Composable
fun AbilityList(modifier: Modifier, abilities: List<UiPokemonAbility>) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Abilities:", fontSize = 26.sp)
        if (abilities.isNotEmpty()) {
            abilities.forEach { ability ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(3.dp, abilityBackground, RoundedCornerShape(5))
                        .padding(8.dp),
                ) {
                    Text(ability.name, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                    Text(ability.effect, fontSize = 22.sp)
                }
            }
        } else {
            Text("Abilities are missing.", fontSize = 20.sp)
        }
    }
}