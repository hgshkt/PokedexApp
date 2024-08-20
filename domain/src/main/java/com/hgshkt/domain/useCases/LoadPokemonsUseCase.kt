package com.hgshkt.domain.useCases

import com.hgshkt.domain.data.PokemonRepository
import com.hgshkt.domain.data.Result

class LoadPokemonsUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend fun execute() {
        val result: Result<List<Int>> = pokemonRepository.needToLoad()
        if (result is Result.Success) {
             pokemonRepository.downloadPokemonsByIdList(idList = result.value)
        }
    }
}