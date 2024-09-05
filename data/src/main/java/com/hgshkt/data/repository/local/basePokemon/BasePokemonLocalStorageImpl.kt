package com.hgshkt.data.repository.local.basePokemon

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.takeWhile

class BasePokemonLocalStorageImpl(
    private val basePokemonDao: BasePokemonDao
) : BasePokemonLocalStorage {
    override fun getBasePokemon(id: Int): LocalBasePokemon {
        return basePokemonDao.get(id)
    }

    override fun getBasePokemons(): List<LocalBasePokemon> {
        return basePokemonDao.getAll()
    }

    override fun saveBasePokemon(pokemon: LocalBasePokemon) {
        basePokemonDao.save(pokemon)
    }

    override suspend fun saveBasePokemons(pokemons: List<LocalBasePokemon>) {
        basePokemonDao.save(pokemons)
    }

    override fun markAsLoaded(id: Int) {
        basePokemonDao.markAsLoaded(id)
    }

    override fun markAsInfoLoaded(id: Int) {
        basePokemonDao.markAsInfoLoaded(id)
    }

    override fun isLoaded(id: Int): Boolean {
        return basePokemonDao.isLoaded(id)
    }

    override fun isInfoLoaded(id: Int): Boolean {
        return basePokemonDao.isInfoLoaded(id)
    }

    override fun loadedAsFlow(): Flow<Int> {
        val count = basePokemonDao.count()
        return basePokemonDao.loadedAsFlow()
            .takeWhile {
                it <= count
            }
    }

    override fun infoLoadedAsFlow(): Flow<Int> {
        val count = basePokemonDao.count()
        return basePokemonDao.infoLoadedAsFlow()
            .takeWhile {
                it <= count
            }
    }

    override fun count(): Flow<Int> {
        return basePokemonDao.countAsFlow()
    }
}