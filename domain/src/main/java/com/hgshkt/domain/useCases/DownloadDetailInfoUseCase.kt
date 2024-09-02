package com.hgshkt.domain.useCases

import com.hgshkt.domain.data.PokemonRepository
import com.hgshkt.domain.data.Result

class DownloadDetailInfoUseCase(
    private val repository: PokemonRepository
) {
    suspend fun execute() {
        val result = repository.needToLoadInfo()
        if (result is Result.Error) {
            repository.downloadBasePokemons()
        } else {
            repository.downloadPokemonAbilitiesByIdList(idList = (result as Result.Success).value)
        }
    }
}