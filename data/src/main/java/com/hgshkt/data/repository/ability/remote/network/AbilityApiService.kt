package com.hgshkt.data.repository.ability.remote.network

import com.hgshkt.data.repository.ability.remote.network.model.ability.ResponseAbility
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AbilityApiService {
    @GET("/api/v2/ability/{id}")
    suspend fun loadAbility(
        @Path("id") id: String
    ): Response<ResponseAbility>
}