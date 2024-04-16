package com.hgshkt.data.repository.ability

import com.hgshkt.data.repository.ability.remote.network.model.ability.ResponseAbility
import com.hgshkt.domain.data.model.DAbility

fun ResponseAbility.toDAbility(): DAbility {
    return DAbility(
        id = id,
        name = name,
        effect = effectEntries.find { it.language.name == "en" }!!.effect
    )
}