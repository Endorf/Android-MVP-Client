package com.mvp.sharednotes.di.module

import android.content.Context
import androidx.room.Room
import com.mvp.sharednotes.data.repository.storage.db.SharedNotesDatabase
import com.mvp.sharednotes.data.repository.storage.db.dao.UserDao
import com.mvp.sharednotes.data.repository.storage.UserDataStore
import com.mvp.sharednotes.data.repository.storage.cloud.RemoteUserDataStore
import com.mvp.sharednotes.data.repository.storage.cloud.api.UserApi
import com.mvp.sharednotes.data.repository.storage.db.LocalUserDataStore
import com.mvp.sharednotes.data.repository.storage.preferences.SharedUserDataStore
import com.mvp.sharednotes.di.qualifier.Local
import com.mvp.sharednotes.di.qualifier.Remote
import com.mvp.sharednotes.di.qualifier.Shared
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
    fun provideUserDao(appDatabase: SharedNotesDatabase): UserDao = appDatabase.userDao()

    @Provides
    @AppScope
    @Local
    fun provideLocalDataStore(userDao: UserDao): UserDataStore = LocalUserDataStore(userDao)

    @Provides
    @AppScope
    @Remote
    fun provideRemoteDataStore(api: UserApi): UserDataStore = RemoteUserDataStore(api)

    @Provides
    @AppScope
    @OptIn(ExperimentalCoroutinesApi::class)
    @Shared
    fun providePreferenceDataStore(context: Context): UserDataStore =
        SharedUserDataStore(context)
}