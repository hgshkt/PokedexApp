package com.hgshkt.data.repository.local

import androidx.paging.PagingSource
import com.hgshkt.data.repository.local.ability.ref.PokemonAbilityCrossRef
import com.hgshkt.data.repository.local.pokemon.PokemonEntity

interface PokemonLocalStorage {
    fun pokemonsAsPagingSource(): PagingSource<Int, PokemonEntity>
    suspend fun updatePokemonEntities(pokemonEntities: List<PokemonEntity>, refresh: Boolean)
    suspend fun saveAbilityRef(pokemonId: Int, abilityId: Int)
    suspend fun getAbilityRefsForPokemon(id: Int): List<PokemonAbilityCrossRef>
}