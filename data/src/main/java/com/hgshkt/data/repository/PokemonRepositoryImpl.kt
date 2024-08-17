package com.hgshkt.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.hgshkt.data.repository.local.PokemonDatabase
import com.hgshkt.data.repository.local.PokemonLocalStorage
import com.hgshkt.data.repository.mappers.toAbility
import com.hgshkt.data.repository.mappers.toDPokemon
import com.hgshkt.data.repository.mappers.toEntity
import com.hgshkt.data.repository.mappers.toPokemon
import com.hgshkt.data.repository.mappers.toSimplePokemon
import com.hgshkt.data.repository.remote.PokemonRemoteStorage
import com.hgshkt.data.repository.remote.ability.AbilityRemoteStorage
import com.hgshkt.domain.data.PokemonRepository
import com.hgshkt.domain.data.PokemonResponse
import com.hgshkt.domain.data.mapper.toPokemon
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
    private val pokemonLocalStorage: PokemonLocalStorage,
    private val pokemonRemoteStorage: PokemonRemoteStorage
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

    override suspend fun getPokemon(id: Int): PokemonResponse<Pokemon> {
        // try loading from local storage
        with(pokemonLocalStorage) {
            getPokemon(id)?.let {
                val abilities = getAbilityRefsForPokemon(id).mapNotNull { ref ->
                    loadAbilityById(ref.abilityId)
                }

                return PokemonResponse.Success(it.toPokemon(abilities))
            }
        }

        // in other case load from remote storage
        val response = pokemonRemoteStorage.getPokemon(id)
        if (response.isSuccessful) {
            val body = response.body()!!
            val abilities = body.abilities.map {
                // load ability from api
                abilityRemoteStorage.getAbility(it.ability?.url!!).also { ability ->
                    // save ability to local db
                    pokemonLocalStorage.saveAbility(ability!!.toAbility().toEntity())
                }
            }
            val pokemon = body.toDPokemon().toPokemon(abilities.map { it!!.toAbility() })
            return PokemonResponse.Success(pokemon)
        } else {
            return PokemonResponse.Error(response.message())
        }
    }

    private suspend fun loadAbilityById(abilityId: Int): Ability? {
        val localAbility = pokemonLocalStorage.getAbility(abilityId)

        if (localAbility == null) {
            val remoteAbility = abilityRemoteStorage.getAbility(abilityId)?.toAbility()?.toEntity()

            remoteAbility?.let { remoteAbilityNotNull ->
                pokemonLocalStorage.saveAbility(remoteAbilityNotNull)
                return remoteAbility.toAbility()
            }
            return null
        }
        else {
            return localAbility.toAbility()
        }
    }
}