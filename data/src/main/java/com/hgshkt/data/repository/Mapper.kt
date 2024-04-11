package com.hgshkt.data.repository

import com.hgshkt.data.repository.network.model.ability.ResponseAbility
import com.hgshkt.data.repository.network.model.finalPokemon.FinalPokemonDTO
import com.hgshkt.domain.data.model.DAbility
import com.hgshkt.domain.data.model.DPokemon

fun FinalPokemonDTO.toDPokemon(): DPokemon {
    return DPokemon(
        id = id ?: -1,
        name = name ?: "null name",
        imageUrl = sprites?.other?.officialArtwork?.frontDefault ?: "null imageUrl",
        abilitiesUrl = abilities.map {
            it.ability?.url ?: "null ability"
        }
    )
}

fun ResponseAbility.toDAbility(): DAbility {
    return DAbility(
        id = id,
        name = name,
        effect = effectEntries.find { it.language.name == "en" }!!.effect
    )
}