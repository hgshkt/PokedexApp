package com.hgshkt.pokedex.ui.data.mapper

import com.hgshkt.domain.data.PokemonFilter
import com.hgshkt.pokedex.ui.screens.list.FilterMenuState

fun FilterMenuState.toDomainSettings(): PokemonFilter.Settings {
    return PokemonFilter.Settings(
        types = selectedTypes.map { it.type.toDomain() },
        text = text,
        weightRange = stringsToIntRange(
            weightStart,
            weightEnd,
            FilterMenuState.WEIGHT_START_DEFAULT,
            FilterMenuState.WEIGHT_END_DEFAULT
        ),
        heightRange = stringsToIntRange(
            heightStart,
            heightEnd,
            FilterMenuState.HEIGHT_START_DEFAULT,
            FilterMenuState.HEIGHT_END_DEFAULT
        ),
    )
}

fun stringsToIntRange(start: String, end: String, defaultStart: Int, defaultEnd: Int): IntRange {
    val intStart = if (start.isEmpty()) defaultStart else start.toInt()
    val intEnd = if (end.isEmpty()) defaultEnd else end.toInt()
    return intStart..intEnd
}