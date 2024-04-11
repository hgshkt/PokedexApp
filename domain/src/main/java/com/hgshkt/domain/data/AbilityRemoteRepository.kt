package com.hgshkt.domain.data

import com.hgshkt.domain.data.model.DAbility

interface AbilityRemoteRepository {
    fun getAbility(url: String): DAbility
}