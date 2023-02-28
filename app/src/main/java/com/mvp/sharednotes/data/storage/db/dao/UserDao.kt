package com.mvp.sharednotes.data.storage.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mvp.sharednotes.data.storage.db.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE email LIKE :email LIMIT :limit")
    fun read(email: String, limit: Int = 1): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: UserEntity)
}