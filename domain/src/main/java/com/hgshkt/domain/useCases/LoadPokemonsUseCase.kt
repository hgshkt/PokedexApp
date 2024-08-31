package com.hgshkt.domain.useCases

import com.hgshkt.domain.data.PokemonRepository
import com.hgshkt.domain.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadPokemonsUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend fun execute() = withContext(Dispatchers.IO) {
        var result: Result<List<Int>> = pokemonRepository.needToLoad()
        if (result is Result.Error) {
            pokemonRepository.downloadBasePokemons()
            result = pokemonRepository.needToLoad()
            if (result is Result.Error) return@withContext
        }
        pokemonRepository.downloadPokemonsByIdList(idList = (result as Result.Success).value)
    }
}