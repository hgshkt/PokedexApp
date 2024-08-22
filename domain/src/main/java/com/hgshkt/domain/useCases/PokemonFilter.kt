package com.hgshkt.domain.useCases

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
}