package com.hgshkt.data.repository

import com.hgshkt.data.repository.mappers.toAbility
import com.hgshkt.data.repository.mappers.toPokemon
import com.hgshkt.data.repository.mappers.toSimplePokemon
import com.hgshkt.domain.data.LocalPokemonRepository
import com.hgshkt.domain.data.Result
import com.hgshkt.domain.model.Pokemon
import com.hgshkt.domain.model.SimplePokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalPokemonRepositoryImpl(
    private val storages: PokemonRepositoryStorages
) : LocalPokemonRepository {

    override suspend fun getPokemonsFlow(): Flow<List<SimplePokemon>> {
        return storages.local.pokemon.getAllAsFlow().map { list ->
            list.map { pokemon -> pokemon.toSimplePokemon() }
        }
    }

    override suspend fun getPokemon(id: Int): Result<Pokemon> {
        val abilities = storages.local.pokemonAbilityCrossRef.getAbilityRefsForPokemon(id)
            .map { ref ->
                storages.local.ability.getAbility(ref.abilityId)
                    .toAbility()
            }
        return Result.Success(storages.local.pokemon.getPokemon(id)!!.toPokemon(abilities))
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

    override fun needToLoadInfo(): Result<List<Int>> {
        // try to fetch base pokemons from local storage
        val pokemons = storages.local.basePokemon.getBasePokemons()
        if (pokemons.isNotEmpty()) {
            // return if success
            return Result.Success(
                pokemons
                    // filter downloaded pokemons
                    .filter { pokemon ->
                        !pokemon.infoLoaded
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

    override fun getAllSimplePokemons(): List<SimplePokemon> {
        return storages.local.pokemon.getAll().map { it.toSimplePokemon() }
    }

    override fun markAsLoaded(id: Int) {
        storages.local.basePokemon.markAsLoaded(id)
    }

    override fun markAsInfoLoaded(id: Int) {
        storages.local.basePokemon.markAsInfoLoaded(id)
    }

    override fun isLoaded(id: Int): Boolean {
        return storages.local.basePokemon.isLoaded(id)
    }

    override fun isInfoLoaded(id: Int): Boolean {
        return storages.local.basePokemon.isInfoLoaded(id)
    }


    override fun loadedAsFlow(): Flow<Int> {
        return storages.local.basePokemon.loadedAsFlow()
    }

    override fun infoLoadedAsFlow(): Flow<Int> {
        return storages.local.basePokemon.infoLoadedAsFlow()
    }

    override fun pokemonCount(): Flow<Int> {
        return storages.local.basePokemon.count()
    }
}