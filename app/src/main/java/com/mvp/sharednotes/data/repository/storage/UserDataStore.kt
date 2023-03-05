package com.mvp.sharednotes.data.repository.storage

import com.mvp.sharednotes.data.entity.User
import io.reactivex.rxjava3.core.Single

interface UserDataStore {

    fun create(user: User): Single<User>

    fun get(user: User): Single<User>

    fun get(): Single<User>

    fun update(user: User): Single<User>
}