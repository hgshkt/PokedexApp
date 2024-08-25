package com.hgshkt.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.hgshkt.data.repository.local.PokemonDatabase
import com.hgshkt.data.repository.mappers.toAbility
import com.hgshkt.data.repository.mappers.toDPokemon
import com.hgshkt.data.repository.mappers.toLocal
import com.hgshkt.data.repository.mappers.toPokemon
import com.hgshkt.data.repository.mappers.toSimplePokemon
import com.hgshkt.domain.data.PokemonRepository
import com.hgshkt.domain.data.Result
import com.hgshkt.domain.data.mapper.toPokemon
import com.hgshkt.domain.model.Ability
import com.hgshkt.domain.model.Pokemon
import com.hgshkt.domain.model.SimplePokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokemonRepositoryImpl(
    private val pokemonDatabase: PokemonDatabase,
    private val storages: PokemonRepositoryStorages
) : PokemonRepository {

    override suspend fun getPokemons(): Flow<PagingData<SimplePokemon>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
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

    override suspend fun getPokemon(id: Int): Result<Pokemon> {
        // try loading from local storage
        storages.local.pokemon.getPokemon(id)?.let {
            val abilities = storages.local.pokemonAbilityCrossRef.getAbilityRefsForPokemon(id)
                .mapNotNull { ref ->
                    loadAbilityById(ref.abilityId)
                }
            return Result.Success(it.toPokemon(abilities))
        }


        // in other case load from remote storage
        val response = storages.remote.pokemon.getPokemon(id)
        if (response.isSuccessful) {
            val body = response.body()!!
            val abilities = body.abilities.map {
                // load ability from api
                storages.remote.ability.getAbility(it.ability?.url!!).also { ability ->
                    // save ability to local db
                    storages.local.ability.saveAbility(ability!!.toAbility().toLocal())
                }
            }
            val pokemon = body.toDPokemon().toPokemon(abilities.map { it!!.toAbility() })
            return Result.Success(pokemon)
        } else {
            return Result.Error(response.message())
        }
    }

    override suspend fun downloadBasePokemons() {
        val response = storages.remote.pokemon.getBasePokemons()
        if (response.isSuccessful) {

            val localPokemons = response.body()!!.results.map { remotePokemon ->
                remotePokemon.toLocal()
            }
            storages.local.basePokemon.saveBasePokemons(localPokemons)
        }
    }

    /**
     * Returns ids of pokemons that need complete downloaded
     */
    override fun needToLoad(): Result<List<Int>> {
        // try to fetch base pokemons from local storage
        val pokemons = storages.local.basePokemon.getBasePokemons()
        if (pokemons.isNotEmpty()) {
            // return if success
            return Result.Success(
                pokemons
                    // filter downloaded pokemons
                    .filter { pokemon ->
                        !pokemon.loaded
                    }
                    // return ids
                    .map { pokemon ->
                        pokemon.id
                    }
            )
        }

        // in other case return error result
        else {
            return Result.Error("local pokemon storage is empty")
        }
    }


    override suspend fun downloadPokemonsByIdList(idList: List<Int>) {
        idList.forEach { id ->
            val response = storages.remote.pokemon.getPokemon(id)
            if (response.isSuccessful) {
                storages.local.pokemon.savePokemon(response.body()!!.toLocal())

                storages.local.basePokemon.getBasePokemon(id).let {
                    storages.local.basePokemon.saveBasePokemon(it.apply { loaded = true })
                }
            }
        }
    }

    override fun getAllPokemons(): List<SimplePokemon> {
        return storages.local.pokemon.getAll().map { it.toSimplePokemon() }
    }

    private suspend fun loadAbilityById(abilityId: Int): Ability? {
        val localAbility = storages.local.ability.getAbility(abilityId)

        if (localAbility == null) {
            val remoteAbility =
                storages.remote.ability.getAbility(abilityId)?.toAbility()?.toLocal()

            remoteAbility?.let { remoteAbilityNotNull ->
                storages.local.ability.saveAbility(remoteAbilityNotNull)
                return remoteAbility.toAbility()
            }
            return null
        } else {
            return localAbility.toAbility()
        }
    }
}