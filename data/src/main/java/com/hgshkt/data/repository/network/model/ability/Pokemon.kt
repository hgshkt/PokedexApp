package com.hgshkt.data.repository.network.model.ability

import com.google.gson.annotations.SerializedName


data class Pokemon (
	@SerializedName("is_hidden") val isHidden : Boolean,
	@SerializedName("pokemon") val pokemon : SimplePokemon,
	@SerializedName("slot") val slot : Int
)