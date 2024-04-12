package com.hgshkt.data.repository.pokemon

import com.hgshkt.data.repository.pokemon.network.model.finalPokemon.FinalPokemonDTO
import com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Types
import com.hgshkt.domain.data.model.DPokemon
import com.hgshkt.domain.data.model.DType
import com.hgshkt.domain.data.model.DType.*

fun FinalPokemonDTO.toDPokemon(): DPokemon {
    return DPokemon(
        id = id ?: -1,
        name = name ?: "null name",
        imageUrl = sprites?.other?.officialArtwork?.frontDefault ?: "null imageUrl",
        abilitiesUrl = abilities.map {
            it.ability?.url ?: "null ability"
        },
        types = types.toList().map { it.toDType() }
    )
}

fun Types.toDType(): DType {
    return when (type?.name) {
        "normal" -> NORMAL
        "fire" -> FIRE
        "water" -> WATER
        "electric" -> ELECTRIC
        "grass" -> GRASS
        "ice" -> ICE
        "fighting" -> FIGHTING
        "poison" -> POISON
        "ground" -> GROUND
        "flying" -> FLYING
        "physic" -> PHYSIC
        "bug" -> BUG
        "rock" -> ROCK
        "ghost" -> GHOST
        "dragon" -> DRAGON
        "dark" -> DARK
        "steel" -> STEEL
        "fairy" -> FAIRY
        // unreadable code part
        else -> NORMAL
    }
}