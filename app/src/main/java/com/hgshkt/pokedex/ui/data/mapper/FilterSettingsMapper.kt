package com.hgshkt.pokedex.ui.data.mapper

import com.hgshkt.domain.data.PokemonFilter
import com.hgshkt.pokedex.ui.screens.list.FilterMenuState

fun FilterMenuState.toDomainSettings(): PokemonFilter.Settings {
    return PokemonFilter.Settings(
        types = selectedTypes.map { it.type.toDomain() },
        text = text,
        weightRange = weightStart..weightEnd,
        heightRange = heightStart..heightEnd
    )
}