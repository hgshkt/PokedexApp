package com.hgshkt.data.repository

import androidx.paging.PagingData
import androidx.paging.RemoteMediator
import com.hgshkt.domain.data.PokemonRepository
import com.hgshkt.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

class PokemonRepositoryImpl(
    private val remoteMediator: PokemonRemoteMediator
): PokemonRepository {
    override suspend fun getPokemons(): Flow<PagingData<Pokemon>> {
        TODO("Not yet implemented")
    }
}