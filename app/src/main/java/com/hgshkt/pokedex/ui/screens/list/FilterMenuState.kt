package com.hgshkt.pokedex.ui.screens.list

import com.hgshkt.domain.data.PokemonFilter
import com.hgshkt.pokedex.ui.data.model.UiType

data class FilterMenuState(
    val selectedTypes: List<SelectedType>,
    val text: String = "",
    val weightStart: String = "",
    val weightEnd: String = "",
    val heightStart: String = "",
    val heightEnd: String = "",
    val opened: Boolean = false
) {
    val MAX_SELECTED_TYPES = PokemonFilter.MAX_SELECTED_TYPES
    val MIN_WEIGHT = PokemonFilter.MIN_WEIGHT
    val MAX_WEIGHT = PokemonFilter.MAX_WEIGHT
    val MIN_HEIGHT = PokemonFilter.MIN_HEIGHT
    val MAX_HEIGHT = PokemonFilter.MAX_HEIGHT

    data class SelectedType(
        val type: UiType,
        var selected: Boolean = true
    )
}

