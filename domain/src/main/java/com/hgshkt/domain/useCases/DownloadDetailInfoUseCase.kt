package com.hgshkt.domain.useCases

import com.hgshkt.domain.data.LocalPokemonRepository
import com.hgshkt.domain.data.RemotePokemonRepository
import com.hgshkt.domain.data.Result

class DownloadDetailInfoUseCase(
    private val localRepository: LocalPokemonRepository,
    private val remoteRepository: RemotePokemonRepository
) {
    suspend fun execute() {
        var result = localRepository.needToLoadInfo()
        if (result is Result.Error) {
            remoteRepository.downloadBasePokemons()
            result = localRepository.needToLoad()
            if (result is Result.Error) return
        }
        result as Result.Success

        result.value.forEach { id ->
            val loaded = remoteRepository.downloadInfo(id)
            if(loaded) localRepository.markAsInfoLoaded(id)
            else return
        }
    }
}