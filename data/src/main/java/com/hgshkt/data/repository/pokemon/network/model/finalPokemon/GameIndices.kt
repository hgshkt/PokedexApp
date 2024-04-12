package com.hgshkt.data.repository.pokemon.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class GameIndices(
    @SerializedName("game_index") var gameIndex: Int? = null,
    @SerializedName("version") var version: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Version? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Version()
)
