package com.hgshkt.data.repository.local.basePokemon

import kotlinx.coroutines.flow.Flow

interface BasePokemonLocalStorage {
    fun getBasePokemon(id: Int): LocalBasePokemon
    fun getBasePokemons(): List<LocalBasePokemon>
    fun saveBasePokemon(pokemon: LocalBasePokemon)
    suspend fun saveBasePokemons(pokemons: List<LocalBasePokemon>)
    fun markAsLoaded(id: Int)
    fun markAsInfoLoaded(id: Int)
    fun isLoaded(id: Int): Boolean
    fun isInfoLoaded(id: Int): Boolean
    fun loadedAsFlow(): Flow<Int>
    fun infoLoadedAsFlow(): Flow<Int>
}