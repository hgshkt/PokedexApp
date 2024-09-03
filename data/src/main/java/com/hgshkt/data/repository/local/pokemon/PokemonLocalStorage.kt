package com.hgshkt.data.repository.local.pokemon

import kotlinx.coroutines.flow.Flow

interface PokemonLocalStorage {
    fun getPokemon(id: Int): LocalCompletePokemon?
    fun savePokemon(pokemon: LocalCompletePokemon)
    fun getAll(): List<LocalCompletePokemon>
    fun getAllAsFlow(): Flow<List<LocalCompletePokemon>>
}