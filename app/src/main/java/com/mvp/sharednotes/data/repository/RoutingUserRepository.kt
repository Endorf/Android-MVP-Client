package com.mvp.sharednotes.data.repository

import com.mvp.sharednotes.data.repository.storage.preferences.UserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface RoutingUserRepository {

    fun get(): Single<UserEntity>

    fun create(user: UserEntity): Completable
}