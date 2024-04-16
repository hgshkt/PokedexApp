package com.hgshkt.data.repository.pokemon.remote.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class GenerationVIII(
    @SerializedName("icons") var icons: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Icons? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Icons()
)