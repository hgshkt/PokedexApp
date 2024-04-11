package com.hgshkt.data.repository

import com.hgshkt.data.repository.network.AbilityApiService
import com.hgshkt.domain.data.AbilityRemoteRepository
import com.hgshkt.domain.data.model.DAbility

class AbilityRemoteRepositoryImpl(
    private val abilityApiService: AbilityApiService
): AbilityRemoteRepository {
    override suspend fun getAbility(url: String): DAbility {
        val id = url.split('/').last { it.isNotEmpty() }
        return getAbilityById(id)
    }

    suspend fun getAbilityById(id: String): DAbility {
        return abilityApiService.loadAbility(id).body()?.toDAbility()!!
    }
}