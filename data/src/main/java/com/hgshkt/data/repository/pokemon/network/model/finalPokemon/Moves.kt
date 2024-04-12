package com.hgshkt.data.repository.pokemon.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class Moves(
    @SerializedName("move") var move: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Move? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Move(),
    @SerializedName("version_group_details") var versionGroupDetails: ArrayList<com.hgshkt.data.repository.pokemon.network.model.finalPokemon.VersionGroupDetails> = arrayListOf()
)
