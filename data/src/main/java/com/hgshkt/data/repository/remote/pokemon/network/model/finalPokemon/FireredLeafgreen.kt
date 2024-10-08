package com.hgshkt.data.repository.remote.pokemon.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class FireredLeafgreen(
    @SerializedName("back_default") var backDefault: String? = null,
    @SerializedName("back_shiny") var backShiny: String? = null,
    @SerializedName("front_default") var frontDefault: String? = null,
    @SerializedName("front_shiny") var frontShiny: String? = null
)