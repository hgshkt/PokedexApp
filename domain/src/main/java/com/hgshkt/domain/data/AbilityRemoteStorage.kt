package com.hgshkt.domain.data

import com.hgshkt.domain.data.model.DAbility

interface AbilityRemoteStorage {
    suspend fun getAbility(url: String): DAbility
}