package com.hgshkt.data.repository.mappers

import com.hgshkt.data.repository.local.basePokemon.LocalBasePokemon
import com.hgshkt.data.repository.local.pokemon.LocalCompletePokemon
import com.hgshkt.data.repository.remote.pokemon.network.model.RemoteBasePokemon
import com.hgshkt.data.repository.remote.pokemon.network.model.finalPokemon.RemoteCompletePokemon
import com.hgshkt.data.repository.remote.pokemon.network.model.finalPokemon.Stats
import com.hgshkt.data.repository.remote.pokemon.network.model.finalPokemon.Types
import com.hgshkt.data.util.formatName
import com.hgshkt.data.util.lastParamFromUrl
import com.hgshkt.domain.data.model.DPokemon
import com.hgshkt.domain.data.model.DStats
import com.hgshkt.domain.data.model.DType
import com.hgshkt.domain.data.model.DType.BUG
import com.hgshkt.domain.data.model.DType.DARK
import com.hgshkt.domain.data.model.DType.DRAGON
import com.hgshkt.domain.data.model.DType.ELECTRIC
import com.hgshkt.domain.data.model.DType.FAIRY
import com.hgshkt.domain.data.model.DType.FIGHTING
import com.hgshkt.domain.data.model.DType.FIRE
import com.hgshkt.domain.data.model.DType.FLYING
import com.hgshkt.domain.data.model.DType.GHOST
import com.hgshkt.domain.data.model.DType.GRASS
import com.hgshkt.domain.data.model.DType.GROUND
import com.hgshkt.domain.data.model.DType.ICE
import com.hgshkt.domain.data.model.DType.NORMAL
import com.hgshkt.domain.data.model.DType.PHYSIC
import com.hgshkt.domain.data.model.DType.POISON
import com.hgshkt.domain.data.model.DType.ROCK
import com.hgshkt.domain.data.model.DType.STEEL
import com.hgshkt.domain.data.model.DType.WATER
import com.hgshkt.domain.model.Ability
import com.hgshkt.domain.model.Pokemon
import com.hgshkt.domain.model.SimplePokemon
import com.hgshkt.domain.model.Type
import java.util.Locale

fun RemoteCompletePokemon.toDPokemon(): DPokemon {
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

fun RemoteCompletePokemon.toLocal(format: Boolean = true): LocalCompletePokemon {
    return LocalCompletePokemon(
        id = id ?: -1,
        name = if (format)
            name?.formatName()
        else {
            name
        } ?: "null name",
        imageUrl = sprites?.other?.officialArtwork?.frontDefault ?: "null imageUrl",
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

fun LocalCompletePokemon.toPokemon(abilities: List<Ability>): Pokemon {
    val types = mutableListOf<Type>()
    type1name?.let { types.add(typeByName(it)) }
    type2name?.let { types.add(typeByName(it)) }

    val stats = com.hgshkt.domain.model.Stats(
        hp = hp,
        attack = attack,
        defense = defense,
        specialAttack = specialAttack,
        specialDefense = specialDefense,
        speed = speed
    )

    return Pokemon(
        id = id,
        name = name,
        imageUrl = imageUrl,
        abilities = abilities,
        types = types,
        stats = stats,
        weight = weight,
        height = height
    )
}

fun LocalCompletePokemon.toSimplePokemon(): SimplePokemon {
    val types = mutableListOf<Type>()
    type1name?.let { types.add(typeByName(it)) }
    type2name?.let { types.add(typeByName(it)) }

    val stats = com.hgshkt.domain.model.Stats(
        hp = hp,
        attack = attack,
        defense = defense,
        specialAttack = specialAttack,
        specialDefense = specialDefense,
        speed = speed
    )

    return SimplePokemon(
        id = id,
        name = name.capitalize(Locale.ROOT).replace('-', ' '),
        imageUrl = imageUrl,
        types = types,
        stats = stats,
        weight = weight,
        height = height
    )
}

fun RemoteBasePokemon.toLocal(): LocalBasePokemon {
    return LocalBasePokemon(
        id = url!!.lastParamFromUrl().toInt(),
        name = name!!,
        url = url!!
    )
}

private fun typeByName(name: String): Type {
    return when (name) {
        "normal" -> Type.NORMAL
        "fire" -> Type.FIRE
        "water" -> Type.WATER
        "electric" -> Type.ELECTRIC
        "grass" -> Type.GRASS
        "ice" -> Type.ICE
        "fighting" -> Type.FIGHTING
        "poison" -> Type.POISON
        "ground" -> Type.GROUND
        "flying" -> Type.FLYING
        "physic" -> Type.PSYCHIC
        "bug" -> Type.BUG
        "rock" -> Type.ROCK
        "ghost" -> Type.GHOST
        "dragon" -> Type.DRAGON
        "dark" -> Type.DARK
        "steel" -> Type.STEEL
        "fairy" -> Type.FAIRY
        // unreadable code part
        else -> Type.NORMAL
    }
}

private fun <T> List<T>.getOrNull(index: Int): T? {
    return getOrElse(index) { null }
}

/**
Map Stats from Pokemon json model to Dstats
 */
fun ArrayList<Stats>.toDStats(): DStats {
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