package com.hgshkt.data.repository.local.pokemonAbilityCrossRef

class PokemonAbilityCrossRefLocalStorageImpl(
    private val pokemonAbilityCrossRefDao: PokemonAbilityCrossRefDao,
): PokemonAbilityCrossRefLocalStorage {
    override suspend fun saveAbilityRef(pokemonId: Int, abilityId: Int) {
        pokemonAbilityCrossRefDao.insert(
            PokemonAbilityCrossRef(pokemonId, abilityId)
        )
    }

    override suspend fun getAbilityRefsForPokemon(id: Int): List<PokemonAbilityCrossRef> {
        return pokemonAbilityCrossRefDao.getAbilitiesForPokemon(id)
    }
}