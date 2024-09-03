package com.hgshkt.domain.data

import com.hgshkt.domain.model.Pokemon
import com.hgshkt.domain.model.SimplePokemon
import kotlinx.coroutines.flow.Flow

interface LocalPokemonRepository {
    suspend fun getPokemonsFlow(): Flow<List<SimplePokemon>>
    suspend fun getPokemon(id: Int): Result<Pokemon>
    fun needToLoad(): Result<List<Int>>
    fun needToLoadInfo(): Result<List<Int>>
    fun getAllSimplePokemons(): List<SimplePokemon>
    fun markAsLoaded(id: Int)
    fun markAsInfoLoaded(id: Int)
    fun isLoaded(id: Int): Boolean
    fun isInfoLoaded(id: Int): Boolean
    fun loadedAsFlow(): Flow<Int>
    fun infoLoadedAsFlow(): Flow<Int>
}