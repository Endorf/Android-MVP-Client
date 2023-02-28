package com.mvp.sharednotes.data.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mvp.sharednotes.BuildConfig
import com.mvp.sharednotes.data.storage.db.dao.UserDao
import com.mvp.sharednotes.data.storage.db.entity.UserEntity

@Database(
    version = BuildConfig.DB_VERSION,
    entities = [UserEntity::class],
    exportSchema = true
)
abstract class SharedNotesDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        internal const val DB_NAME = "shared_notes_db"
    }
}