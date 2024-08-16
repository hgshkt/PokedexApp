package com.hgshkt.data.repository.local

import androidx.paging.PagingSource
import com.hgshkt.data.repository.local.ability.AbilityEntity
import com.hgshkt.data.repository.local.ability.ref.PokemonAbilityCrossRef
import com.hgshkt.data.repository.local.pokemon.PokemonEntity
import com.hgshkt.domain.model.Ability

interface PokemonLocalStorage {
    fun pokemonsAsPagingSource(): PagingSource<Int, PokemonEntity>
    suspend fun updatePokemonEntities(pokemonEntities: List<PokemonEntity>, refresh: Boolean)
    suspend fun saveAbilityRef(pokemonId: Int, abilityId: Int)
    suspend fun getAbilityRefsForPokemon(id: Int): List<PokemonAbilityCrossRef>
    suspend fun saveAbility(abilityEntity: AbilityEntity)
    suspend fun getAbility(abilityId: Int): AbilityEntity?
    fun getPokemon(id: Int): PokemonEntity?
}