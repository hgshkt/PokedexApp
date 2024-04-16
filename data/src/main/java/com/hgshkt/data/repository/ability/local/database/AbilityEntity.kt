package com.hgshkt.data.repository.ability.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "abilities")
data class AbilityEntity(
    @PrimaryKey
    var id: Int,
    var name: String,
    var effect: String
)