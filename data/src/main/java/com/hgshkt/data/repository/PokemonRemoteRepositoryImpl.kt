package com.hgshkt.data.repository

import androidx.paging.PagingSource
import com.hgshkt.domain.data.PokemonRemoteRepository
import com.hgshkt.domain.data.model.DPokemon
import com.hgshkt.domain.model.Pokemon

class PokemonRemoteRepositoryImpl(
    private val pagingSource: PokemonsPagingSource
): PokemonRemoteRepository {
    override fun loadPokemons(): PagingSource<Int, DPokemon> {
        return pagingSource
    }
}