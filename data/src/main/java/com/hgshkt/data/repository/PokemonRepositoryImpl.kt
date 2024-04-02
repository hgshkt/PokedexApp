package com.hgshkt.data.repository

import androidx.paging.PagingSource
import com.hgshkt.domain.data.PokemonRemoteRepository
import com.hgshkt.domain.model.Pokemon

class PokemonRepositoryImpl(
    private val repository: PokemonRemoteRepository
): PokemonRemoteRepository {
    override fun loadPokemons(): PagingSource<Int, Pokemon> {
        return repository.loadPokemons()
    }
}