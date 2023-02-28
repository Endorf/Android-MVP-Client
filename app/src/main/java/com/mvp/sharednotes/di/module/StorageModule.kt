package com.mvp.sharednotes.di.module

import android.content.Context
import androidx.room.Room
import com.mvp.sharednotes.data.storage.db.SharedNotesDatabase
import com.mvp.sharednotes.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class StorageModule {

    @AppScope
    @Provides
    fun provideDatabase(context: Context): SharedNotesDatabase {
        return Room.databaseBuilder(
            context,
            SharedNotesDatabase::class.java,
            SharedNotesDatabase.DB_NAME
        ).build()
    }
}