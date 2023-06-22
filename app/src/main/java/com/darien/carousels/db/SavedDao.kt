package com.darien.carousels.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSaved(savedEntity: SavedEntity)

    @Update
    suspend fun updateStatus(savedEntity: SavedEntity)

    @Query("SELECT * FROM saved_items WHERE id = :itemId")
    fun getSaveStatus(itemId: Int) : Flow<SavedEntity>

}