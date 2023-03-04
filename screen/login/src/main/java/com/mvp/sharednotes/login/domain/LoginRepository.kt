package com.mvp.sharednotes.login.domain

import com.mvp.sharednotes.login.view.entity.User
import com.mvp.sharednotes.login.view.entity.UserCredentials
import io.reactivex.rxjava3.core.Single

interface LoginRepository {

    fun get(user: UserCredentials): Single<User>

    fun create(user: UserCredentials): Single<User>
}