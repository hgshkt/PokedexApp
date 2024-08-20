package com.hgshkt.data.repository.local

import androidx.paging.PagingSource
import com.hgshkt.data.repository.local.ability.LocalAbility
import com.hgshkt.data.repository.local.ability.ref.PokemonAbilityCrossRef
import com.hgshkt.data.repository.local.basePokemon.LocalBasePokemon
import com.hgshkt.data.repository.local.pokemon.LocalCompletePokemon

interface PokemonLocalStorage {

    // base pokemon
    fun getBasePokemon(id: Int): LocalBasePokemon?
    fun getBasePokemons(): List<LocalBasePokemon>?

    fun saveBasePokemon(pokemon: LocalBasePokemon)
    suspend fun saveBasePokemons(pokemons: List<LocalBasePokemon>)

    // complete pokemon
    fun pokemonsAsPagingSource(): PagingSource<Int, LocalCompletePokemon>
    fun getPokemon(id: Int): LocalCompletePokemon?

    suspend fun updateCompletePokemons(pokemonEntities: List<LocalCompletePokemon>, refresh: Boolean)
    fun savePokemon(pokemon: LocalCompletePokemon)

    // ability refs
    suspend fun getAbilityRefsForPokemon(id: Int): List<PokemonAbilityCrossRef>
    suspend fun saveAbilityRef(pokemonId: Int, abilityId: Int)

    // ability
    suspend fun getAbility(abilityId: Int): LocalAbility?
    suspend fun saveAbility(localAbility: LocalAbility)

}