package com.hgshkt.domain.data.mapper

import com.hgshkt.domain.data.model.DAbility
import com.hgshkt.domain.model.Ability

fun DAbility.toAbility(): Ability {
    return Ability(
        id = id,
        name = name,
        effect = effect
    )
}