package com.hgshkt.data.repository.local.ability

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "abilities")
data class AbilityEntity(
    @PrimaryKey
    var id: Int,
    var name: String,
    var effect: String
)