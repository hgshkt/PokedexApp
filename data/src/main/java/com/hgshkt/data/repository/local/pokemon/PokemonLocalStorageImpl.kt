package com.hgshkt.data.repository.local.pokemon

class PokemonLocalStorageImpl(
    private val pokemonDao: PokemonDao
) : PokemonLocalStorage {

    override fun savePokemon(pokemon: LocalCompletePokemon) {
        pokemonDao.save(pokemon)
    }

    override fun getAll(): List<LocalCompletePokemon> {
        return pokemonDao.getAll()
    }


    override fun getPokemon(id: Int): LocalCompletePokemon? {
        return pokemonDao.get(id).first()
    }
}