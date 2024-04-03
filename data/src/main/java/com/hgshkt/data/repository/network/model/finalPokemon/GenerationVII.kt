package com.hgshkt.data.repository.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class GenerationVII(
    @SerializedName("icons") var icons: Icons? = Icons(),
    @SerializedName("ultra-sun-ultra-moon") var ultraSunUltraMoon: UltraSunUltraMoon? = UltraSunUltraMoon()
)