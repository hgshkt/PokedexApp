package com.hgshkt.data.repository.pokemon.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class Versions(
    @SerializedName("generation-i") var generationI: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.GenerationI? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.GenerationI(),
    @SerializedName("generation-ii") var generationII: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.GenerationII? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.GenerationII(),
    @SerializedName("generation-iii") var generationIII: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.GenerationIII? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.GenerationIII(),
    @SerializedName("generation-iv") var generationIV: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.GenerationIV? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.GenerationIV(),
    @SerializedName("generation-v") var generationV: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.GenerationV? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.GenerationV(),
    @SerializedName("generation-vi") var generationVI: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.GenerationVI? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.GenerationVI(),
    @SerializedName("generation-vii") var generationVII: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.GenerationVII? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.GenerationVII(),
    @SerializedName("generation-viii") var generationVIII: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.GenerationVIII? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.GenerationVIII()
)