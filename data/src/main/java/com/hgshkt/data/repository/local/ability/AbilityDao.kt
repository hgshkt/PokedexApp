package com.hgshkt.data.repository.local.ability

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AbilityDao {
    @Insert
    suspend fun insert(ability: AbilityEntity)

    @Query("SELECT * FROM abilities WHERE id = :id")
    suspend fun get(id: Int): AbilityEntity
}