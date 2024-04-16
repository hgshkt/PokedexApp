package com.hgshkt.data.repository.local

import androidx.paging.PagingSource
import com.hgshkt.data.repository.local.ability.AbilityDao
import com.hgshkt.data.repository.local.ability.ref.PokemonAbilityCrossRefDao
import com.hgshkt.data.repository.local.pokemon.PokemonDao
import com.hgshkt.data.repository.local.pokemon.PokemonEntity

class PokemonLocalStorageImpl(
    private val pokemonDao: PokemonDao,
    private val abilityDao: AbilityDao,
    private val pokemonAbilityCrossRefDao: PokemonAbilityCrossRefDao
): PokemonLocalStorage {
    override fun pokemonsAsPagingSource(): PagingSource<Int, PokemonEntity> {
        return pokemonDao.pagingSource()
    }
}