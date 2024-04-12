package com.hgshkt.data.repository.pokemon.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("base_stat") var baseStat: Int? = null,
    @SerializedName("effort") var effort: Int? = null,
    @SerializedName("stat") var stat: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Stat? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Stat()
)