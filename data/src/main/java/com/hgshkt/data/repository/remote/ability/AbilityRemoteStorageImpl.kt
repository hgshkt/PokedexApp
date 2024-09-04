package com.hgshkt.data.repository.remote.ability

import com.hgshkt.data.repository.remote.ability.network.AbilityApiService
import com.hgshkt.data.repository.remote.ability.network.model.ability.ResponseAbility
import retrofit2.Response

class AbilityRemoteStorageImpl(
    private val abilityApiService: AbilityApiService
): AbilityRemoteStorage {

    /**
     * Return null if response not successful.
     */
    override suspend fun getAbility(id: Int): ResponseAbility? {
        try {
            val response = abilityApiService.loadAbility(id)
            if(response.isSuccessful) {
                return response.body()
            } else if (response.message() == "No internet connection") {
                return null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

        return null
    }

    override suspend fun getAbility(url: String): ResponseAbility? {
        val abilityId = url.split('/').last { it.isNotEmpty() }
        return getAbility(abilityId)
    }
}