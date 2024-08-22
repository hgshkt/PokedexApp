package com.hgshkt.domain.data

import com.hgshkt.domain.useCases.PokemonFilter
import kotlinx.coroutines.flow.flow

class FilterPokemonsUseCase(
    private val filter: PokemonFilter,
    private val repository: PokemonRepository
) {
    fun execute(
        settings: PokemonFilter.Settings
    ) = flow {
        val result = repository.needToLoad()
        if (result is Result.Success && result.value.isEmpty()) {
            val pokemons = repository.getAllPokemons()
            emit(Result.Success(filter.filter(settings, pokemons)))
        }
        emit(Result.Error("Need to load all pokemons before filter them"))
    }
}