package com.hgshkt.domain.useCases

import androidx.paging.PagingSource
import com.hgshkt.domain.data.PokemonRemoteRepository
import com.hgshkt.domain.model.Pokemon


class LoadPokemonsUseCase(
    private val remoteRepository: PokemonRemoteRepository
) {
    fun execute(): PagingSource<Int, Pokemon> {
        return remoteRepository.loadPokemons()
    }
}