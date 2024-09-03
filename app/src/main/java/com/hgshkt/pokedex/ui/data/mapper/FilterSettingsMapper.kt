package com.hgshkt.pokedex.ui.data.mapper

import com.hgshkt.domain.data.PokemonFilter
import com.hgshkt.pokedex.ui.screens.list.filter.FilterMenuState

fun FilterMenuState.toDomainSettings(): PokemonFilter.Settings {
    return PokemonFilter.Settings(
        types = selectedTypes.map { it.type.toDomain() },
        text = text,
        weightRange = stringsToIntRange(
            weightStart,
            weightEnd,
            PokemonFilter.MIN_WEIGHT,
            PokemonFilter.MAX_WEIGHT
        ),
        heightRange = stringsToIntRange(
            heightStart,
            heightEnd,
            PokemonFilter.MIN_HEIGHT,
            PokemonFilter.MAX_HEIGHT
        ),
    )
}

fun stringsToIntRange(start: String, end: String, defaultStart: Int, defaultEnd: Int): IntRange {
    val intStart = if (start.isEmpty()) defaultStart else start.toInt()
    val intEnd = if (end.isEmpty()) defaultEnd else end.toInt()
    return intStart..intEnd
}