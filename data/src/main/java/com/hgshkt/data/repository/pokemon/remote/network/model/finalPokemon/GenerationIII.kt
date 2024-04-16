package com.hgshkt.data.repository.pokemon.remote.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class GenerationIII(
    @SerializedName("emerald") var emerald: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Emerald? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Emerald(),
    @SerializedName("firered-leafgreen") var fireredLeafgreen: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.FireredLeafgreen? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.FireredLeafgreen(),
    @SerializedName("ruby-sapphire") var rubySapphire: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.RubySapphire? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.RubySapphire()
)