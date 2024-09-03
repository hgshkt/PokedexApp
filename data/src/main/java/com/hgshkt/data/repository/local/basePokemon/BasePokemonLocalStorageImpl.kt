package com.hgshkt.data.repository.local.basePokemon

class BasePokemonLocalStorageImpl(
    private val basePokemonDao: BasePokemonDao
): BasePokemonLocalStorage {
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
}