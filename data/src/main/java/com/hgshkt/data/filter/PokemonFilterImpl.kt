package com.hgshkt.data.filter

import com.hgshkt.domain.model.SimplePokemon
import com.hgshkt.domain.useCases.PokemonFilter

class PokemonFilterImpl : PokemonFilter {
    override fun filter(
        settings: PokemonFilter.Settings,
        pokemons: List<SimplePokemon>
    ): List<SimplePokemon> {
        with(settings) {
            return pokemons
                .weightInRange(weightRange)
                .heightInRange(heightRange)
        }
    }

    private fun List<SimplePokemon>.weightInRange(range: IntRange): List<SimplePokemon> {
        return filter { it.weight in range }
    }

    private fun List<SimplePokemon>.heightInRange(range: IntRange): List<SimplePokemon> {
        return filter { it.height in range }
    }
}