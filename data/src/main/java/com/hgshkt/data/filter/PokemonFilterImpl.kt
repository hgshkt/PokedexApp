package com.hgshkt.data.filter

import com.hgshkt.domain.model.SimplePokemon
import com.hgshkt.domain.model.Type
import com.hgshkt.domain.data.PokemonFilter
import com.hgshkt.domain.model.Pokemon

class PokemonFilterImpl : PokemonFilter {
    override fun filter(
        settings: PokemonFilter.Settings,
        pokemons: List<SimplePokemon>
    ): List<SimplePokemon> {
        with(settings) {
//            // if input settings is default not need to filter
//            if (settings == PokemonFilter.Settings()) return pokemons
//
            return pokemons
                .weightInRange(weightRange)
                .heightInRange(heightRange)
                .type(types)
                .nameContains(text)
        }
    }

    private fun List<SimplePokemon>.nameContains(text: String): List<SimplePokemon> {
        return filter { it.name.contains(text, true) }
    }

    private fun List<SimplePokemon>.type(types: List<Type>): List<SimplePokemon> {
        return filter { pokemon ->
            pokemon.types.all { pokemonType ->
                types.all { neededType ->
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