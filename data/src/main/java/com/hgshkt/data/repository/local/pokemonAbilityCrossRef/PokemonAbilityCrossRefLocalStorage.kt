package com.hgshkt.data.repository.local.pokemonAbilityCrossRef

interface PokemonAbilityCrossRefLocalStorage {
    suspend fun getAbilityRefsForPokemon(id: Int): List<PokemonAbilityCrossRef>
    suspend fun saveAbilityRef(pokemonId: Int, abilityId: Int)
}