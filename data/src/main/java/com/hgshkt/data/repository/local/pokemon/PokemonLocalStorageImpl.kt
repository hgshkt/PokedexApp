package com.hgshkt.data.repository.local.pokemon

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.hgshkt.data.repository.local.PokemonDatabase
import com.hgshkt.data.repository.local.ability.AbilityDao
import com.hgshkt.data.repository.local.ability.LocalAbility
import com.hgshkt.data.repository.local.pokemonAbilityCrossRef.PokemonAbilityCrossRef
import com.hgshkt.data.repository.local.pokemonAbilityCrossRef.PokemonAbilityCrossRefDao

class PokemonLocalStorageImpl(
    private val pokemonDao: PokemonDao,
    private val pokemonDatabase: PokemonDatabase
) : PokemonLocalStorage {

    override fun pokemonsAsPagingSource(): PagingSource<Int, LocalCompletePokemon> {
        return pokemonDao.pagingSource()
    }

    override suspend fun updateCompletePokemons(
        pokemonEntities: List<LocalCompletePokemon>,
        refresh: Boolean
    ) {
        pokemonDatabase.withTransaction {
            if (refresh) {
                pokemonDatabase.pokemonDao.deleteAll()
            }
            pokemonDatabase.pokemonDao.upsertAll(pokemonEntities)
        }
    }

    override fun savePokemon(pokemon: LocalCompletePokemon) {
        pokemonDao.save(pokemon)
    }


    override fun getPokemon(id: Int): LocalCompletePokemon? {
        return pokemonDao.get(id).first()
    }
}