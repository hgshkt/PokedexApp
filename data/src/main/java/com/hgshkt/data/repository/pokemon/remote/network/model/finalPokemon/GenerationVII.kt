package com.hgshkt.data.repository.pokemon.remote.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class GenerationVII(
    @SerializedName("icons") var icons: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Icons? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Icons(),
    @SerializedName("ultra-sun-ultra-moon") var ultraSunUltraMoon: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.UltraSunUltraMoon? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.UltraSunUltraMoon()
)