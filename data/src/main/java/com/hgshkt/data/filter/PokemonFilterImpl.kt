package com.hgshkt.data.filter

import com.hgshkt.domain.model.SimplePokemon
import com.hgshkt.domain.model.Type
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
                .type(types)
                .nameContains(text)
        }
    }


    private fun List<SimplePokemon>.nameContains(text: String): List<SimplePokemon> {
        return filter { it.name.contains(text) }
    }

    private fun List<SimplePokemon>.type(types: List<Type>): List<SimplePokemon> {
        return filter { pokemon ->
            pokemon.types.all { pokemonType ->
                types.any { neededType ->
                    neededType == pokemonType
                }
            }
        }
    }

    private fun List<SimplePokemon>.weightInRange(range: IntRange): List<SimplePokemon> {
        return filter { it.weight in range }
    }

    private fun List<SimplePokemon>.heightInRange(range: IntRange): List<SimplePokemon> {
        return filter { it.height in range }
    }
}