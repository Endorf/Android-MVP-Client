package com.mvp.sharednotes.data.repository.storage.cloud

import com.mvp.sharednotes.data.entity.User
import com.mvp.sharednotes.data.repository.storage.UserDataStore
import com.mvp.sharednotes.data.repository.storage.cloud.api.UserApi
import com.mvp.sharednotes.data.repository.storage.cloud.entity.toUser
import com.mvp.sharednotes.data.repository.storage.cloud.entity.toUserEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteUserDataStore @Inject constructor(
    private val api: UserApi
) : UserDataStore {

    override fun create(user: User): Single<User> = api.create(
        user.toUserEntity()
    ).map { it.toUser() }

    override fun get(user: User): Single<User> = api.get(
        MOCK_ID
    ).map { it.toUser() }

    override fun get(): Single<User> {
        TODO("Not yet implemented")
    }

    override fun update(user: User): Single<User> = api.update(
        MOCK_ID,
        user.toUserEntity()
    ).map { it.toUser() }

    companion object {
        private const val MOCK_ID = 1
    }
}