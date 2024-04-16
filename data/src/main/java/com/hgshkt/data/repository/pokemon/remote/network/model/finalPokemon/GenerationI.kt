package com.hgshkt.data.repository.pokemon.remote.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class GenerationI(
    @SerializedName("red-blue") var redBlue: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.RedBlue? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.RedBlue(),
    @SerializedName("yellow") var yellow: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Yellow? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Yellow()
)