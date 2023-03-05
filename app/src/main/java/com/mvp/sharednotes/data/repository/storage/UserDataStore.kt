package com.mvp.sharednotes.data.repository.storage

import com.mvp.sharednotes.data.repository.storage.preferences.UserEntity
import io.reactivex.rxjava3.core.Single

interface UserDataStore {

    fun create(user: UserEntity): Single<UserEntity>

    fun get(): Single<UserEntity>
}