package com.hgshkt.data.repository.pokemon.network.model.finalPokemon

import com.google.gson.annotations.SerializedName


data class GenerationII(
    @SerializedName("crystal") var crystal: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Crystal? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Crystal(),
    @SerializedName("gold") var gold: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Gold? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Gold(),
    @SerializedName("silver") var silver: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Silver? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Silver()
)