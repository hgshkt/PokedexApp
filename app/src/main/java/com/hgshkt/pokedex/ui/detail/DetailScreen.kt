package com.hgshkt.pokedex.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.hgshkt.domain.model.Pokemon

@Composable
fun DetailScreen(pokemon: Pokemon) {
    Column {
        Text("Detail Screen")
        Text(pokemon.name)
    }
}
