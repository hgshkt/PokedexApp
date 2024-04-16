package com.hgshkt.data.repository.remote.pokemon

import androidx.paging.PagingSource
import com.hgshkt.data.repository.PokemonsPagingSource
import com.hgshkt.domain.data.PokemonRemoteRepository
import com.hgshkt.domain.data.model.DPokemon

class PokemonRemoteRepositoryImpl(
    private val pagingSource: PokemonsPagingSource
): PokemonRemoteRepository {
    override fun loadPokemons(): PagingSource<Int, DPokemon> {
        return pagingSource
    }
}