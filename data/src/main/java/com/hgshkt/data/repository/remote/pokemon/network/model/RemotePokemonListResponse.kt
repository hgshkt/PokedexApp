package com.hgshkt.data.repository.remote.pokemon.network.model

import com.google.gson.annotations.SerializedName

data class RemotePokemonListResponse(
    @SerializedName("count") var count: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("previous") var previous: String? = null,
    @SerializedName("results") var results: List<RemoteBasePokemon> = listOf()
)
