package com.hgshkt.data.repository.remote.pokemon.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class Icons(
    @SerializedName("front_default") var frontDefault: String? = null,
    @SerializedName("front_female") var frontFemale: String? = null
)