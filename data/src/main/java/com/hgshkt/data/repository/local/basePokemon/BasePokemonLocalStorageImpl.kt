package com.hgshkt.data.repository.local.basePokemon

class BasePokemonLocalStorageImpl(
    private val basePokemonDao: BasePokemonDao
): BasePokemonLocalStorage {
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
}