package com.hgshkt.data.repository.pokemon.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class Other(
    @SerializedName("dream_world") var dreamWorld: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.DreamWorld? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.DreamWorld(),
    @SerializedName("home") var home: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Home? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Home(),
    @SerializedName("official-artwork") var officialArtwork: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.OfficialArtwork? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.OfficialArtwork(),
    @SerializedName("showdown") var showdown: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Showdown? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Showdown()
)