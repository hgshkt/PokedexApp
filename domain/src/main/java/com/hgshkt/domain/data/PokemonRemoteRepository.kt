package com.hgshkt.domain.data

import androidx.paging.Pager
import com.hgshkt.domain.model.Pokemon

interface PokemonRemoteRepository {
    fun loadPokemons(): Pager<Int, Pokemon>
}