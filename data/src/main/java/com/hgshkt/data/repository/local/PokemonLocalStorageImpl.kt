package com.hgshkt.data.repository.local

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.hgshkt.data.repository.local.ability.AbilityDao
import com.hgshkt.data.repository.local.ability.LocalAbility
import com.hgshkt.data.repository.local.ability.ref.PokemonAbilityCrossRef
import com.hgshkt.data.repository.local.ability.ref.PokemonAbilityCrossRefDao
import com.hgshkt.data.repository.local.basePokemon.BasePokemonDao
import com.hgshkt.data.repository.local.basePokemon.LocalBasePokemon
import com.hgshkt.data.repository.local.pokemon.PokemonDao
import com.hgshkt.data.repository.local.pokemon.LocalCompletePokemon

class PokemonLocalStorageImpl(
    private val basePokemonDao: BasePokemonDao,
    private val pokemonDao: PokemonDao,
    private val abilityDao: AbilityDao,
    private val pokemonAbilityCrossRefDao: PokemonAbilityCrossRefDao,
    private val pokemonDatabase: PokemonDatabase
) : PokemonLocalStorage {
    override fun getBasePokemon(id: Int): LocalBasePokemon? {
        return basePokemonDao.get(id)
    }

    override fun getBasePokemons(): List<LocalBasePokemon>? {
        return basePokemonDao.getAll()
    }

    override fun saveBasePokemon(pokemon: LocalBasePokemon) {
        basePokemonDao.save(pokemon)
    }

    override suspend fun saveBasePokemons(pokemons: List<LocalBasePokemon>) {
        basePokemonDao.save(pokemons)
    }

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

    override suspend fun saveAbilityRef(pokemonId: Int, abilityId: Int) {
        pokemonAbilityCrossRefDao.insert(
            PokemonAbilityCrossRef(pokemonId, abilityId)
        )
    }

    override suspend fun getAbilityRefsForPokemon(id: Int): List<PokemonAbilityCrossRef> {
        return pokemonAbilityCrossRefDao.getAbilitiesForPokemon(id)
    }

    override suspend fun saveAbility(localAbility: LocalAbility) {
        abilityDao.insert(localAbility)
    }

    override suspend fun getAbility(abilityId: Int): LocalAbility? {
        return abilityDao.get(abilityId)
    }

    override fun getPokemon(id: Int): LocalCompletePokemon? {
        return pokemonDao.get(id).first()
    }
}