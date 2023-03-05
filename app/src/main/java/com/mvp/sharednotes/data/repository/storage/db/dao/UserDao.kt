package com.mvp.sharednotes.data.repository.storage.db.dao

import androidx.room.*
import com.mvp.sharednotes.data.repository.storage.db.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE email LIKE :email LIMIT :limit")
    fun read(email: String, limit: Int = 1): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: UserEntity)

    @Update
    fun update(vararg users: UserEntity): Int
}