package com.hgshkt.pokedex.data.mapper

import com.hgshkt.domain.model.Ability
import com.hgshkt.domain.model.Pokemon
import com.hgshkt.domain.model.Stats
import com.hgshkt.domain.model.Type
import com.hgshkt.pokedex.data.model.UiPokemon
import com.hgshkt.pokedex.data.model.UiPokemonAbility
import com.hgshkt.pokedex.data.model.UiStats
import com.hgshkt.pokedex.data.model.UiType

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
        Type.PHYSIC -> UiType.PHYSIC
        Type.BUG -> UiType.BUG
        Type.ROCK -> UiType.ROCK
        Type.GHOST -> UiType.GHOST
        Type.DRAGON -> UiType.DRAGON
        Type.DARK -> UiType.DARK
        Type.STEEL -> UiType.STEEL
        Type.FAIRY -> UiType.FAIRY
    }
}

private fun Stats.toUi(): UiStats {
    return UiStats(hp, attack, defense, specialAttack, specialDefense, speed)
}
