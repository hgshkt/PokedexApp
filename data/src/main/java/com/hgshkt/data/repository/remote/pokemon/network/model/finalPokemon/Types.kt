package com.hgshkt.data.repository.remote.pokemon.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class Types(
    @SerializedName("slot") var slot: Int? = null,
    @SerializedName("type") var type: Type? = Type()
)