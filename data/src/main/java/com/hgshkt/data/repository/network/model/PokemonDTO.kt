package com.hgshkt.data.repository.network.model

import com.google.gson.annotations.SerializedName

data class PokemonDTO(
    @SerializedName("height") var height: Int? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("weight") var weight: Int? = null
)
