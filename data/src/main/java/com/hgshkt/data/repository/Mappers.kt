package com.hgshkt.data.repository

import com.hgshkt.data.repository.network.model.PokemonFromResponseDTO
import com.hgshkt.domain.model.Pokemon

internal fun PokemonFromResponseDTO.toPokemon(): Pokemon {
    return Pokemon(
        id = id ?: -1,
        name = name ?: "null name"
    )
}