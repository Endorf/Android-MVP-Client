package com.mvp.sharednotes.data.repository.storage.db

import com.mvp.sharednotes.data.entity.User
import com.mvp.sharednotes.data.repository.storage.UserDataStore
import com.mvp.sharednotes.data.repository.storage.db.dao.UserDao
import com.mvp.sharednotes.data.repository.storage.db.entity.mapper.toUser
import com.mvp.sharednotes.data.repository.storage.db.entity.mapper.toUserEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LocalUserDataStore @Inject constructor(
    private val database: UserDao
) : UserDataStore {

    override fun create(user: User): Single<User> = Single.create {
        database.insert(user.toUserEntity())
        it.onSuccess(user)
    }

    override fun get(user: User): Single<User> = Single.create {
        it.onSuccess(
            database.read(user.email).toUser()
        )
    }

    // TODO: return all users
    override fun get(): Single<User> = TODO("Not yet implemented. ")
}