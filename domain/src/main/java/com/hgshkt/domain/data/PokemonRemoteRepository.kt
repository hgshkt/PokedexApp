package com.hgshkt.domain.data

import androidx.paging.PagingSource
import com.hgshkt.domain.data.model.DPokemon
import com.hgshkt.domain.model.Pokemon

interface PokemonRemoteRepository {
    fun loadPokemons(): PagingSource<Int, DPokemon>
}