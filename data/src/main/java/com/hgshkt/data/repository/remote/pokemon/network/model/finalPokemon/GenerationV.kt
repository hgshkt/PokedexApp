package com.hgshkt.data.repository.remote.pokemon.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class GenerationV(
    @SerializedName("black-white") var blackWhite: BlackWhite? = BlackWhite()
)