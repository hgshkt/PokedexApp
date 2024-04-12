package com.hgshkt.data.repository.ability.network.model.ability

import com.google.gson.annotations.SerializedName


data class Name (
    @SerializedName("language") val language : Language,
    @SerializedName("name") val name : String
)