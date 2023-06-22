package com.darien.carousels.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Constants.SAVED_TABLE_NAME)
data class SavedEntity(
    @PrimaryKey
    val id: Int,
    val isSaved: Boolean
) {
    companion object {
        fun fromSavedToEntity(saved: Saved) : SavedEntity = SavedEntity(id = saved.id, isSaved = saved.isSaved)
    }
    fun toSavedFromEntity() : Saved = Saved(id, isSaved)
}
