package com.hgshkt.data.repository.remote.pokemon.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class GenerationVIII(
    @SerializedName("icons") var icons: Icons? = Icons()
)