package com.hgshkt.data.repository.local

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.hgshkt.data.repository.local.ability.AbilityDao
import com.hgshkt.data.repository.local.ability.AbilityEntity
import com.hgshkt.data.repository.local.ability.ref.PokemonAbilityCrossRef
import com.hgshkt.data.repository.local.ability.ref.PokemonAbilityCrossRefDao
import com.hgshkt.data.repository.local.pokemon.PokemonDao
import com.hgshkt.data.repository.local.pokemon.PokemonDatabase
import com.hgshkt.data.repository.local.pokemon.PokemonEntity

class PokemonLocalStorageImpl(
    private val pokemonDao: PokemonDao,
    private val abilityDao: AbilityDao,
    private val pokemonAbilityCrossRefDao: PokemonAbilityCrossRefDao,
    private val pokemonDatabase: PokemonDatabase
) : PokemonLocalStorage {
    override fun pokemonsAsPagingSource(): PagingSource<Int, PokemonEntity> {
        return pokemonDao.pagingSource()
    }

    override suspend fun updatePokemonEntities(
        pokemonEntities: List<PokemonEntity>,
        refresh: Boolean
    ) {
        pokemonDatabase.withTransaction {
            if (refresh) {
                pokemonDatabase.pokemonDao.deleteAll()
            }
            pokemonDatabase.pokemonDao.upsertAll(pokemonEntities)
        }
    }

    override suspend fun saveAbilityRef(pokemonId: Int, abilityId: Int) {
        pokemonAbilityCrossRefDao.insert(
            PokemonAbilityCrossRef(pokemonId, abilityId)
        )
    }

    override suspend fun getAbilityRefsForPokemon(id: Int): List<PokemonAbilityCrossRef> {
        return pokemonAbilityCrossRefDao.getAbilitiesForPokemon(id)
    }

    override suspend fun saveAbility(abilityEntity: AbilityEntity) {
        abilityDao.insert(abilityEntity)
    }
}