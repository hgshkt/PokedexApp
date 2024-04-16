package com.hgshkt.data.repository.pokemon.remote.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class Sprites(
    @SerializedName("back_default") var backDefault: String? = null,
    @SerializedName("back_female") var backFemale: String? = null,
    @SerializedName("back_shiny") var backShiny: String? = null,
    @SerializedName("back_shiny_female") var backShinyFemale: String? = null,
    @SerializedName("front_default") var frontDefault: String? = null,
    @SerializedName("front_female") var frontFemale: String? = null,
    @SerializedName("front_shiny") var frontShiny: String? = null,
    @SerializedName("front_shiny_female") var frontShinyFemale: String? = null,
    @SerializedName("other") var other: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Other? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Other(),
    @SerializedName("versions") var versions: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Versions? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Versions()
)