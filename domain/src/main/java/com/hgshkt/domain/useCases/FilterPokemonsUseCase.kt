package com.hgshkt.domain.useCases

import com.hgshkt.domain.data.PokemonFilter
import com.hgshkt.domain.data.PokemonRepository
import com.hgshkt.domain.data.Result
import com.hgshkt.domain.model.SimplePokemon

class FilterPokemonsUseCase(
    private val filter: PokemonFilter,
    private val repository: PokemonRepository
) {
    fun execute(
        settings: PokemonFilter.Settings
    ): Result<List<SimplePokemon>> {

        val result = repository.needToLoad()
        if (result is Result.Success && result.value.isEmpty()) {
            val pokemons = repository.getAllPokemons()
            return Result.Success(filter.filter(settings, pokemons))
        }
        return Result.Error("Need to load all pokemons before filter them")
    }
}