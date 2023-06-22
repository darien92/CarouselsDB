package com.darien.carousels.db

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class SavedDBDataSource @Inject constructor(private val savedDao: SavedDao) {
    suspend fun saveStatus(savedModel: Saved) {
        val savedEntity = SavedEntity.fromSavedToEntity(savedModel)
        if(!statusExists(id = savedModel.id)) {
            savedDao.addSaved(savedEntity = savedEntity)
        } else {
            savedDao.updateStatus(savedEntity = savedEntity)
        }
    }

    fun getSavedStatus(id: Int) : Flow<SavedEntity> {
        return savedDao.getSaveStatus(itemId = id)
    }

    private suspend fun statusExists(id: Int): Boolean {
        val matchingStatus = savedDao.getSaveStatus(itemId = id).firstOrNull()
        return matchingStatus != null
    }
}