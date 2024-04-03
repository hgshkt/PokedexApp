package com.hgshkt.data.repository.network.model

import com.google.gson.annotations.SerializedName

data class PokemonResponseDTO(
    @SerializedName("count") var count: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("previous") var previous: String? = null,
    @SerializedName("results") var results: List<PokemonFromResponseDTO> = listOf()
)
