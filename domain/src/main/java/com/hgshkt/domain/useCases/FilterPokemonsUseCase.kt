package com.hgshkt.domain.useCases

import com.hgshkt.domain.data.PokemonFilter
import com.hgshkt.domain.data.LocalPokemonRepository
import com.hgshkt.domain.data.Result
import com.hgshkt.domain.model.SimplePokemon

class FilterPokemonsUseCase(
    private val filter: PokemonFilter,
    private val repository: LocalPokemonRepository
) {
    fun execute(
        settings: PokemonFilter.Settings
    ): Result<List<SimplePokemon>> {

        val result = repository.needToLoad()
        if (result is Result.Success && result.value.isEmpty()) {
            val allPokemons = repository.getAllSimplePokemons()
            val filterPokemons = filter.filter(settings, allPokemons)

            if (filterPokemons.isEmpty()) return Result.Error("No Pokemon fits the condition")

            return Result.Success(filterPokemons)
        }
        return Result.Error("Need to load all pokemons before filter them")
    }
}