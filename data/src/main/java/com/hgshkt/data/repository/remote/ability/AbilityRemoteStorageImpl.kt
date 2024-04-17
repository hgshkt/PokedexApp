package com.hgshkt.data.repository.remote.ability

import com.hgshkt.data.repository.remote.ability.network.AbilityApiService
import com.hgshkt.data.repository.remote.ability.network.model.ability.ResponseAbility

class AbilityRemoteStorageImpl(
    private val abilityApiService: AbilityApiService
): AbilityRemoteStorage {
    override suspend fun getAbility(id: Int): ResponseAbility? {
        return abilityApiService.loadAbility(id).body()
    }
}