package com.hgshkt.data.repository.remote.ability

import com.hgshkt.data.repository.remote.ability.network.model.ability.ResponseAbility

interface AbilityRemoteStorage {
    suspend fun getAbility(id: Int): ResponseAbility?
}