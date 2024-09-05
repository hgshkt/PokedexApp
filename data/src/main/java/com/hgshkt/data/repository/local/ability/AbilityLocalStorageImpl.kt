package com.hgshkt.data.repository.local.ability

class AbilityLocalStorageImpl(
    private val abilityDao: AbilityDao
): AbilityLocalStorage {
    override suspend fun saveAbility(localAbility: LocalAbility) {
        abilityDao.insert(localAbility)
    }

    override suspend fun getAbility(abilityId: Int): LocalAbility? {
        return abilityDao.get(abilityId)
    }
}