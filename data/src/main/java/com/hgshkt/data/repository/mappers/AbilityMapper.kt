package com.hgshkt.data.repository.mappers

import com.hgshkt.data.repository.remote.ability.network.model.ability.ResponseAbility
import com.hgshkt.domain.data.model.DAbility

fun ResponseAbility.toDAbility(): DAbility {
    return DAbility(
        id = id,
        name = name,
        effect = effectEntries.find { it.language.name == "en" }!!.effect
    )
}