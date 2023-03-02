package com.mvp.sharednotes.di.module

import android.content.Context
import androidx.room.Room
import com.mvp.sharednotes.data.repository.storage.db.SharedNotesDatabase
import com.mvp.sharednotes.data.repository.storage.preferences.UserInfoDataStore
import com.mvp.sharednotes.data.repository.storage.preferences.UserInfoDataStoreImpl
import com.mvp.sharednotes.di.scope.AppScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
class StorageModule {

    @Provides
    @AppScope
    fun provideDatabase(context: Context): SharedNotesDatabase {
        return Room.databaseBuilder(
            context,
            SharedNotesDatabase::class.java,
            SharedNotesDatabase.DB_NAME
        ).build()
    }

    @Provides
    @AppScope
    @OptIn(ExperimentalCoroutinesApi::class)
    fun provideUserInfoDataStore(context: Context): UserInfoDataStore =
        UserInfoDataStoreImpl(context)
}