package com.hgshkt.data.repository

import com.hgshkt.data.repository.network.model.PokemonFromResponseDTO
import com.hgshkt.data.repository.network.model.finalPokemon.FinalPokemonDTO
import com.hgshkt.domain.model.Pokemon

internal fun FinalPokemonDTO.toPokemon(): Pokemon {
    return Pokemon(
        id = id ?: -1,
        name = name ?: "null name",
        imageUrl = sprites?.other?.officialArtwork?.frontDefault ?: "null imageUrl"
    )
}