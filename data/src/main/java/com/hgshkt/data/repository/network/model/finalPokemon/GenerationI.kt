package com.hgshkt.data.repository.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class GenerationI(
    @SerializedName("red-blue") var redBlue: RedBlue? = RedBlue(),
    @SerializedName("yellow") var yellow: Yellow? = Yellow()
)