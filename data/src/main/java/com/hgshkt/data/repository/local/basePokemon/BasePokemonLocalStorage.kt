package com.hgshkt.data.repository.local.basePokemon

interface BasePokemonLocalStorage {
    fun getBasePokemon(id: Int): LocalBasePokemon?
    fun getBasePokemons(): List<LocalBasePokemon>?

    fun saveBasePokemon(pokemon: LocalBasePokemon)
    suspend fun saveBasePokemons(pokemons: List<LocalBasePokemon>)
}