package com.hgshkt.domain.data

import com.hgshkt.domain.model.SimplePokemon
import com.hgshkt.domain.model.Type

interface PokemonFilter {
    fun filter(
        settings: Settings,
        pokemons: List<SimplePokemon>
    ): List<SimplePokemon>

    data class Settings(
        var types: List<Type>,
        var text: String,
        var weightRange: IntRange,
        var heightRange: IntRange,
    )

    companion object {
        const val MAX_SELECTED_TYPES = 2
        const val MIN_WEIGHT = 0
        const val MAX_WEIGHT = 9999
        const val MIN_HEIGHT = 0
        const val MAX_HEIGHT = 9999
    }
}