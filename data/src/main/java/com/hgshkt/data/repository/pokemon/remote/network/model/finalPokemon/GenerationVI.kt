package com.hgshkt.data.repository.pokemon.remote.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class GenerationVI(
    @SerializedName("omegaruby-alphasapphire") var omegarubyAlphasapphire: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.OmegarubyAlphasapphire? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.OmegarubyAlphasapphire(),
    @SerializedName("x-y") var xy: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.XY? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.XY()
)