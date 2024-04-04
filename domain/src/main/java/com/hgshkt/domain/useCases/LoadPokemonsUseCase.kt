package com.hgshkt.domain.useCases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.hgshkt.domain.data.PokemonRemoteRepository
import com.hgshkt.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow


class LoadPokemonsUseCase(
    private val remoteRepository: PokemonRemoteRepository
) {
    fun execute(): Flow<PagingData<Pokemon>> = Pager(
        PagingConfig(pageSize = 20, prefetchDistance = 20)
    ) {
        remoteRepository.loadPokemons()
    }
        .flow
}