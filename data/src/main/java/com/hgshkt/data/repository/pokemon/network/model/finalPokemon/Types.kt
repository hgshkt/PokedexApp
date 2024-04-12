package com.hgshkt.data.repository.pokemon.network.model.finalPokemon

import com.google.gson.annotations.SerializedName
import com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Type

data class Types(
    @SerializedName("slot") var slot: Int? = null,
    @SerializedName("type") var type: Type? = Type()
)