package com.hgshkt.data.repository.pokemon.remote.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class Cries (
    @SerializedName("latest") val latest: String? = null,
    @SerializedName("legacy") val legacy: String? = null,
)