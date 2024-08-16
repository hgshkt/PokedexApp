package com.hgshkt.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.hgshkt.data.repository.local.PokemonLocalStorage
import com.hgshkt.data.repository.local.PokemonDatabase
import com.hgshkt.data.repository.mappers.toAbility
import com.hgshkt.data.repository.mappers.toEntity
import com.hgshkt.data.repository.mappers.toPokemon
import com.hgshkt.data.repository.mappers.toSimplePokemon
import com.hgshkt.data.repository.remote.ability.AbilityRemoteStorage
import com.hgshkt.domain.data.PokemonRepository
import com.hgshkt.domain.model.Ability
import com.hgshkt.domain.model.Pokemon
import com.hgshkt.domain.model.SimplePokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class PokemonRepositoryImpl(
    private val remoteMediator: PokemonRemoteMediator,
    private val pokemonDatabase: PokemonDatabase,
    private val abilityRemoteStorage: AbilityRemoteStorage,
    private val pokemonLocalStorage: PokemonLocalStorage
) : PokemonRepository {

    override suspend fun getPokemons(): Flow<PagingData<SimplePokemon>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = remoteMediator,
            pagingSourceFactory = {
                pokemonDatabase.pokemonDao.pagingSource()
            }
        ).flow
            .map { pagingData ->
                pagingData.map { pokemonEntity ->
                    pokemonEntity.toSimplePokemon()
                }
            }
    }
}