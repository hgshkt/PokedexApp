package com.hgshkt.domain.data.mapper

import com.hgshkt.domain.data.model.DPokemon
import com.hgshkt.domain.model.Ability
import com.hgshkt.domain.model.Pokemon

fun DPokemon.toPokemon(abilities: List<Ability>): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        imageUrl = imageUrl,
        abilities = abilities
    )
}