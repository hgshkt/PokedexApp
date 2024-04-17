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
import com.hgshkt.data.repository.remote.ability.AbilityRemoteStorage
import com.hgshkt.domain.data.PokemonRepository
import com.hgshkt.domain.model.Ability
import com.hgshkt.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class PokemonRepositoryImpl(
    private val remoteMediator: PokemonRemoteMediator,
    private val pokemonDatabase: PokemonDatabase,
    private val abilityRemoteStorage: AbilityRemoteStorage,
    private val pokemonLocalStorage: PokemonLocalStorage
) : PokemonRepository {

    private val fakeAbility = Ability(id = -1, name = "undefined", effect = "undefined")
    override suspend fun getPokemons(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = remoteMediator,
            pagingSourceFactory = {
                pokemonDatabase.pokemonDao.pagingSource()
            }
        ).flow
            .map { pagingData ->
                pagingData.map { pokemonEntity ->
                    with(pokemonEntity) {
                        val abilities = mutableListOf<Ability>()

                        val refs = pokemonLocalStorage.getAbilityRefsForPokemon(id)

                        // Receive abilities from remote by refs.
                        refs.forEach { ref ->
                            var ability = abilityRemoteStorage.getAbility(ref.abilityId)?.toAbility()
                            // If abilities not received, receive from local database.
                            // --- for example Ability(name = "undefined", effect = "undefined")
                            if (ability == null) {
                                ability = pokemonLocalStorage.getAbility(ref.abilityId)?.toAbility()
                                    ?: fakeAbility
                            } else {
                                // Else -> save ability (without refs) to local db
                                pokemonLocalStorage.saveAbility(ability.toEntity()) // entity
                            }
                            abilities.add(ability)
                        }


                        // pass abilities into toPokemon(abilities)
                        toPokemon(abilities)
                    }
                }
            }
    }
}