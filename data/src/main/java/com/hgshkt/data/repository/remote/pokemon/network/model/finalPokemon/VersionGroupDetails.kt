package com.hgshkt.data.repository.remote.pokemon.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class VersionGroupDetails(
    @SerializedName("level_learned_at") var levelLearnedAt: Int? = null,
    @SerializedName("move_learn_method") var moveLearnMethod: MoveLearnMethod? = MoveLearnMethod(),
    @SerializedName("version_group") var versionGroup: VersionGroup? = VersionGroup()
)
