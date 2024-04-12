package com.hgshkt.data.repository.pokemon.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class GenerationV(
    @SerializedName("black-white") var blackWhite: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.BlackWhite? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.BlackWhite()
)