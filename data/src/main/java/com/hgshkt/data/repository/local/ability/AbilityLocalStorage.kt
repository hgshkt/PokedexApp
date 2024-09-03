package com.hgshkt.data.repository.local.ability

interface AbilityLocalStorage {
    suspend fun getAbility(abilityId: Int): LocalAbility
    suspend fun saveAbility(localAbility: LocalAbility)
}