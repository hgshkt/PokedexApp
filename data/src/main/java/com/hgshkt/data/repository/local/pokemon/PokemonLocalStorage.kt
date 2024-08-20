package com.hgshkt.data.repository.local.pokemon

import androidx.paging.PagingSource
import com.hgshkt.data.repository.local.ability.LocalAbility
import com.hgshkt.data.repository.local.pokemonAbilityCrossRef.PokemonAbilityCrossRef

interface PokemonLocalStorage {
    fun pokemonsAsPagingSource(): PagingSource<Int, LocalCompletePokemon>
    fun getPokemon(id: Int): LocalCompletePokemon?
    suspend fun updateCompletePokemons(pokemonEntities: List<LocalCompletePokemon>, refresh: Boolean)
    fun savePokemon(pokemon: LocalCompletePokemon)
}