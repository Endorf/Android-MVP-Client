package com.mvp.sharednotes.data.repository

import com.mvp.sharednotes.data.entity.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface RoutingUserRepository {

    fun get(): Single<User>

    fun create(user: User): Completable
}