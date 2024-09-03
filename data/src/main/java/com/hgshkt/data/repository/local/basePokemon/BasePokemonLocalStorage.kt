package com.hgshkt.data.repository.local.basePokemon

interface BasePokemonLocalStorage {
    fun getBasePokemon(id: Int): LocalBasePokemon
    fun getBasePokemons(): List<LocalBasePokemon>
    fun saveBasePokemon(pokemon: LocalBasePokemon)
    suspend fun saveBasePokemons(pokemons: List<LocalBasePokemon>)
    fun markAsLoaded(id: Int)
    fun markAsInfoLoaded(id: Int)
    fun isLoaded(id: Int): Boolean
    fun isInfoLoaded(id: Int): Boolean
}