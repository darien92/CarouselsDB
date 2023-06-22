package com.darien.carousels.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SavedEntity::class], version = Constants.DB_VERSION)
abstract class DatabaseService : RoomDatabase(){
    abstract fun savedDao() : SavedDao
}