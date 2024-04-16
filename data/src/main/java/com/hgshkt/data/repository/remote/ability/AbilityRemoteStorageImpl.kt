package com.hgshkt.data.repository.remote.ability

import com.hgshkt.data.repository.remote.ability.network.AbilityApiService
import com.hgshkt.data.repository.mappers.toDAbility
import com.hgshkt.domain.data.AbilityRemoteStorage
import com.hgshkt.domain.data.model.DAbility

class AbilityRemoteStorageImpl(
    private val abilityApiService: AbilityApiService
): AbilityRemoteStorage {
    override suspend fun getAbility(url: String): DAbility {
        val id = url.split('/').last { it.isNotEmpty() }
        return getAbilityById(id)
    }

    suspend fun getAbilityById(id: String): DAbility {
        return abilityApiService.loadAbility(id).body()?.toDAbility()!!
    }
}