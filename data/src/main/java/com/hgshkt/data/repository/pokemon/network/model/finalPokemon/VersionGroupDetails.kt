package com.hgshkt.data.repository.pokemon.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class VersionGroupDetails(
    @SerializedName("level_learned_at") var levelLearnedAt: Int? = null,
    @SerializedName("move_learn_method") var moveLearnMethod: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.MoveLearnMethod? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.MoveLearnMethod(),
    @SerializedName("version_group") var versionGroup: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.VersionGroup? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.VersionGroup()
)
