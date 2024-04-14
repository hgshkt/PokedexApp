package com.hgshkt.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.hgshkt.data.repository.pokemon.local.database.PokemonDatabase
import com.hgshkt.data.repository.pokemon.toPokemon
import com.hgshkt.domain.data.AbilityRemoteRepository
import com.hgshkt.domain.data.PokemonRepository
import com.hgshkt.domain.data.mapper.toAbility
import com.hgshkt.domain.model.Ability
import com.hgshkt.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class PokemonRepositoryImpl(
    private val remoteMediator: PokemonRemoteMediator,
    private val pokemonDatabase: PokemonDatabase,
    private val abilityRemoteRepository: AbilityRemoteRepository
) : PokemonRepository {
    override suspend fun getPokemons(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = remoteMediator,
            pagingSourceFactory = {
                pokemonDatabase.dao.pagingSource()
            }
        ).flow
            .map { pagingData ->
                pagingData.map { pokemonEntity ->
                    with(pokemonEntity) {
                        val abilities = mutableListOf<Ability>()

                        loadAbility(ability1Url) { result -> abilities.add(result) }
                        loadAbility(ability2Url) { result -> abilities.add(result) }
                        loadAbility(ability3Url) { result -> abilities.add(result) }

                        toPokemon(abilities)
                    }
                }
            }
    }

    private suspend fun loadAbility(url: String?, onResult: (Ability) -> Unit) {
        url?.let {
            val ability = abilityRemoteRepository
                .getAbility(it)
                .toAbility()

            onResult(ability)
        }
    }
}