package com.hgshkt.data.repository.pokemon.remote.network.model

import com.google.gson.annotations.SerializedName

/**
 * PokemonFromResponseDTO contains only name and url to complete pokemon model
 */
data class PokemonFromResponseDTO(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
)
