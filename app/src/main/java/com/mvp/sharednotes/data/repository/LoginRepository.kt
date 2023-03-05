package com.mvp.sharednotes.data.repository

import com.mvp.sharednotes.login.view.entity.User
import com.mvp.sharednotes.login.view.entity.UserCredentials
import io.reactivex.rxjava3.core.Single

interface LoginRepository {

    fun get(credentials: UserCredentials): Single<User>

    fun create(credentials: UserCredentials): Single<User>
}