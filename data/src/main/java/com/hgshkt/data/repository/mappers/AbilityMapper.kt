package com.hgshkt.data.repository.mappers

import com.hgshkt.data.repository.local.ability.LocalAbility
import com.hgshkt.data.repository.remote.ability.network.model.ability.ResponseAbility
import com.hgshkt.data.util.formatName
import com.hgshkt.domain.model.Ability

fun ResponseAbility.toAbility(format: Boolean = true): Ability {
    return Ability(
        id = id,
        name = if (format) name.formatName() else name,
        effect = effectEntries.find { it.language.name == "en" }?.effect ?: "undefined"
    )
}

fun Ability.toLocal(): LocalAbility {
    return LocalAbility(
        id = id,
        name = name,
        effect = effect
    )
}

fun LocalAbility.toAbility(): Ability {
    return Ability(
        id = id,
        name = name,
        effect = effect
    )
}