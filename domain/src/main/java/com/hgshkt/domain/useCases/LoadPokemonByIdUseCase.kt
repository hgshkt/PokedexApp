package com.hgshkt.domain.useCases

import com.hgshkt.domain.data.LocalPokemonRepository
import com.hgshkt.domain.data.RemotePokemonRepository
import com.hgshkt.domain.data.Result
import com.hgshkt.domain.model.Pokemon

class LoadPokemonByIdUseCase(
    private val localPokemonRepository: LocalPokemonRepository,
    private val remotePokemonRepository: RemotePokemonRepository
) {
    suspend fun execute(id: Int): Result<Pokemon> {
        if (localPokemonRepository.isLoaded(id).not()) {
            remotePokemonRepository.downloadPokemon(id)
            localPokemonRepository.markAsLoaded(id)
        }

        if (localPokemonRepository.isInfoLoaded(id).not()) {
            remotePokemonRepository.downloadInfo(id)
            localPokemonRepository.markAsInfoLoaded(id)
        }

        return localPokemonRepository.getPokemon(id)
    }
}