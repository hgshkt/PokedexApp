package com.hgshkt.data.repository.mappers

import com.hgshkt.data.repository.local.ability.AbilityEntity
import com.hgshkt.data.repository.remote.ability.network.model.ability.ResponseAbility
import com.hgshkt.domain.model.Ability

fun ResponseAbility.toAbility(): Ability {
    return Ability(
        id = id,
        name = name,
        effect = effectEntries.find { it.language.name == "en" }?.effect ?: "undefined"
    )
}

fun Ability.toEntity(): AbilityEntity {
    return AbilityEntity(
        id = id,
        name = name,
        effect = effect
    )
}

fun AbilityEntity.toAbility(): Ability {
    return Ability(
        id = id,
        name = name,
        effect = effect
    )
}