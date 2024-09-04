package com.hgshkt.domain.useCases

import com.hgshkt.domain.data.LocalPokemonRepository
import com.hgshkt.domain.data.RemotePokemonRepository
import com.hgshkt.domain.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoadPokemonsUseCase(
    private val localRepository: LocalPokemonRepository,
    private val remoteRepository: RemotePokemonRepository
) {
    suspend fun execute() = withContext(Dispatchers.IO) {
        var result: Result<List<Int>> = localRepository.needToLoad()
        if (result is Result.Error) {
            remoteRepository.downloadBasePokemons()
            result = localRepository.needToLoad()
            if (result is Result.Error) return@withContext
        }
        result as Result.Success

        result.value.forEach { id ->
            val loaded = remoteRepository.downloadPokemon(id)
            if (loaded) localRepository.markAsLoaded(id)
            else return@withContext
        }
    }
}