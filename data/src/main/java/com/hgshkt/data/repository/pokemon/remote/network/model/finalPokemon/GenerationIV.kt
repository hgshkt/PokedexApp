package com.hgshkt.data.repository.pokemon.remote.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class GenerationIV(
    @SerializedName("diamond-pearl") var diamondPearl: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.DiamondPearl? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.DiamondPearl(),
    @SerializedName("heartgold-soulsilver") var heartgoldSoulsilver: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.HeartgoldSoulsilver? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.HeartgoldSoulsilver(),
    @SerializedName("platinum") var platinum: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Platinum? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Platinum()
)