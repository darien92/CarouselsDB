package com.darien.carousels.db

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DBRepository @Inject constructor(private val savedDS: SavedDBDataSource){
    suspend fun saveStatus(id: Int, isSaved : Boolean) {
        val savedModel = Saved(id = id, isSaved = isSaved)
        savedDS.saveStatus(savedModel = savedModel)
    }

    fun getStatus(id: Int) : Flow<Boolean> {
        return savedDS.getSavedStatus(id = id).map { entity ->
            entity.isSaved
        }
    }
}