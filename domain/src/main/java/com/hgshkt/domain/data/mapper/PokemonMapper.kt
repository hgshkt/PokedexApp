package com.hgshkt.domain.data.mapper

import com.hgshkt.domain.data.model.DPokemon
import com.hgshkt.domain.data.model.DStats
import com.hgshkt.domain.data.model.DType
import com.hgshkt.domain.model.Ability
import com.hgshkt.domain.model.Pokemon
import com.hgshkt.domain.model.Stats
import com.hgshkt.domain.model.Type

fun DPokemon.toPokemon(abilities: List<Ability>): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        imageUrl = imageUrl,
        abilities = abilities,
        types = types.map { it.toType() },
        stats = stats.toStats(),
        weight = weight,
        height = height
    )
}

fun DType.toType(): Type {
    return when (this) {
        DType.NORMAL -> Type.NORMAL
        DType.FIRE -> Type.FIRE
        DType.WATER -> Type.WATER
        DType.ELECTRIC -> Type.ELECTRIC
        DType.GRASS -> Type.GRASS
        DType.ICE -> Type.ICE
        DType.FIGHTING -> Type.FIGHTING
        DType.POISON -> Type.POISON
        DType.GROUND -> Type.GROUND
        DType.FLYING -> Type.FLYING
        DType.PHYSIC -> Type.PSYCHIC
        DType.BUG -> Type.BUG
        DType.ROCK -> Type.ROCK
        DType.GHOST -> Type.GHOST
        DType.DRAGON -> Type.DRAGON
        DType.DARK -> Type.DARK
        DType.STEEL -> Type.STEEL
        DType.FAIRY -> Type.FAIRY
    }
}

fun DStats.toStats(): Stats {
    return Stats(
        hp = hp,
        attack = attack,
        defense = defense,
        specialAttack = specialAttack,
        specialDefense = specialDefense,
        speed = speed,
        total = total
    )
}