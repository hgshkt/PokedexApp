package com.hgshkt.data.repository.local.pokemon

interface PokemonLocalStorage {
    fun getPokemon(id: Int): LocalCompletePokemon?
    fun savePokemon(pokemon: LocalCompletePokemon)
    fun getAll(): List<LocalCompletePokemon>
}