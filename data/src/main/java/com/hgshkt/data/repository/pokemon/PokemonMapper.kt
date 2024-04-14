package com.hgshkt.data.repository.pokemon

import com.hgshkt.data.repository.pokemon.local.model.PokemonEntity
import com.hgshkt.data.repository.pokemon.network.model.finalPokemon.FinalPokemonDTO
import com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Stats
import com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Types
import com.hgshkt.domain.data.model.DPokemon
import com.hgshkt.domain.data.model.DStats
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
        types = types.toList().map { it.toDType() },
        stats = stats.toDStats(),
        weight = weight ?: -1,
        height = height ?: -1
    )
}

fun FinalPokemonDTO.toEntity(): PokemonEntity {
    return PokemonEntity(
        id = id ?: -1,
        name = name ?: "null name",
        imageUrl = sprites?.other?.officialArtwork?.frontDefault ?: "null imageUrl",
        ability1Url = abilities.getOrNull(0)?.ability?.url,
        ability2Url = abilities.getOrNull(1)?.ability?.url,
        ability3Url = abilities.getOrNull(2)?.ability?.url,
        type1name = types.getOrNull(0)?.type?.name,
        type2name = types.getOrNull(1)?.type?.name,
        weight = weight ?: -1,
        height = height ?: -1,
        hp = stats.find { it.stat?.name == "hp" }?.baseStat ?: -1,
        attack = stats.find { it.stat?.name == "attack" }?.baseStat ?: -1,
        defense = stats.find { it.stat?.name == "defense" }?.baseStat ?: -1,
        specialAttack = stats.find { it.stat?.name == "special-attack" }?.baseStat ?: -1,
        specialDefense = stats.find { it.stat?.name == "special-defense" }?.baseStat ?: -1,
        speed = stats.find { it.stat?.name == "speed" }?.baseStat ?: -1
    )
}

private fun <T> List<T>.getOrNull(index: Int): T? {
    return getOrElse(0) { null }
}

fun  ArrayList<Stats>.toDStats(): DStats {
    return DStats(
        hp = find { it.stat?.name == "hp" }?.baseStat ?: -1,
        attack = find { it.stat?.name == "attack" }?.baseStat ?: -1,
        defense = find { it.stat?.name == "defense" }?.baseStat ?: -1,
        specialAttack = find { it.stat?.name == "special-attack" }?.baseStat ?: -1,
        specialDefense = find { it.stat?.name == "special-defense" }?.baseStat ?: -1,
        speed = find { it.stat?.name == "speed" }?.baseStat ?: -1,
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