package com.hgshkt.domain.data

import com.hgshkt.domain.model.SimplePokemon
import com.hgshkt.domain.useCases.PokemonFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FilterPokemonsUseCase(
    private val filter: PokemonFilter,
    private val repository: PokemonRepository
) {
    fun execute(
        settings: PokemonFilter.Settings
    ): Result<List<SimplePokemon>>  {

        val result = repository.needToLoad()
        if (result is Result.Success && result.value.isEmpty()) {
            val pokemons = repository.getAllPokemons()
            return Result.Success(filter.filter(settings, pokemons))
        }
        return Result.Error("Need to load all pokemons before filter them")
    }
}