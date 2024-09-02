package com.hgshkt.pokedex.ui.data.mapper

import com.hgshkt.domain.model.Ability
import com.hgshkt.domain.model.Pokemon
import com.hgshkt.domain.model.SimplePokemon
import com.hgshkt.domain.model.Stats
import com.hgshkt.domain.model.Type
import com.hgshkt.pokedex.ui.data.model.UiPokemon
import com.hgshkt.pokedex.ui.data.model.UiPokemonAbility
import com.hgshkt.pokedex.ui.data.model.UiSimplePokemon
import com.hgshkt.pokedex.ui.data.model.UiStats
import com.hgshkt.pokedex.ui.data.model.UiType

fun Pokemon.toUi(): UiPokemon {
    return UiPokemon(
        id = id,
        name = name,
        imageUrl = imageUrl,
        abilities = abilities.map { it.toUi() },
        types = types.map { it.toUi() },
        stats = stats.toUi(),
        weight = weight,
        height = height
    )
}

fun SimplePokemon.toUi(): UiSimplePokemon {
    return UiSimplePokemon(
        id = id,
        name = name,
        imageUrl = imageUrl,
        types = types.map { it.toUi() },
        stats = stats.toUi(),
        weight = weight,
        height = height
    )
}

fun Ability.toUi(): UiPokemonAbility {
    return UiPokemonAbility(id, name, effect)
}

fun Type.toUi(): UiType {
    return when(this) {
        Type.NORMAL -> UiType.NORMAL
        Type.FIRE -> UiType.FIRE
        Type.WATER -> UiType.WATER
        Type.ELECTRIC -> UiType.ELECTRIC
        Type.GRASS -> UiType.GRASS
        Type.ICE -> UiType.ICE
        Type.FIGHTING -> UiType.FIGHTING
        Type.POISON -> UiType.POISON
        Type.GROUND -> UiType.GROUND
        Type.FLYING -> UiType.FLYING
        Type.PSYCHIC -> UiType.PHYSIC
        Type.BUG -> UiType.BUG
        Type.ROCK -> UiType.ROCK
        Type.GHOST -> UiType.GHOST
        Type.DRAGON -> UiType.DRAGON
        Type.DARK -> UiType.DARK
        Type.STEEL -> UiType.STEEL
        Type.FAIRY -> UiType.FAIRY
    }
}

fun UiType.toDomain(): Type {
    return when(this) {
        UiType.NORMAL -> Type.NORMAL
        UiType.FIRE -> Type.FIRE
        UiType.WATER -> Type.WATER
        UiType.ELECTRIC -> Type.ELECTRIC
        UiType.GRASS -> Type.GRASS
        UiType.ICE -> Type.ICE
        UiType.FIGHTING -> Type.FIGHTING
        UiType.POISON -> Type.POISON
        UiType.GROUND -> Type.GROUND
        UiType.FLYING -> Type.FLYING
        UiType.PHYSIC -> Type.PSYCHIC
        UiType.BUG -> Type.BUG
        UiType.ROCK -> Type.ROCK
        UiType.GHOST -> Type.GHOST
        UiType.DRAGON -> Type.DRAGON
        UiType.DARK -> Type.DARK
        UiType.STEEL -> Type.STEEL
        UiType.FAIRY -> Type.FAIRY
    }
}

private fun Stats.toUi(): UiStats {
    return UiStats(hp, attack, defense, specialAttack, specialDefense, speed)
}
