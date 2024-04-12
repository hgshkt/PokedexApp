package com.hgshkt.data.repository.pokemon.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class Move(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
)
