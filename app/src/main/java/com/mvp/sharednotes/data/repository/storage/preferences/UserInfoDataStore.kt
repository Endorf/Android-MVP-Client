package com.mvp.sharednotes.data.repository.storage.preferences

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface UserInfoDataStore {

    fun create(user: UserEntity): Completable

    fun get(): Single<UserEntity>
}