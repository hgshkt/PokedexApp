package com.hgshkt.data.repository.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class Emerald(
    @SerializedName("front_default") var frontDefault: String? = null,
    @SerializedName("front_shiny") var frontShiny: String? = null
)