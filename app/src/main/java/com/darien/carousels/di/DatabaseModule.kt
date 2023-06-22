package com.darien.carousels.di

import android.content.Context
import androidx.room.Room
import com.darien.carousels.db.Constants
import com.darien.carousels.db.DatabaseService
import com.darien.carousels.db.SavedDao
import com.darien.carousels.repository.ItemsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDBService(@ApplicationContext context: Context) : DatabaseService {
        return Room.databaseBuilder(
            context,
            DatabaseService::class.java,
            Constants.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideSavedDao(appDatabase: DatabaseService) : SavedDao = appDatabase.savedDao()

    @Provides
    fun provideRepo() : ItemsRepository = ItemsRepository()
}