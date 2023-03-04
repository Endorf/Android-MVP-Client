package com.mvp.sharednotes.login.domain

import com.mvp.sharednotes.login.view.entity.User
import com.mvp.sharednotes.login.view.entity.UserCredentials
import io.reactivex.rxjava3.core.Single

interface LoginInteractor {

    fun login(credentials: UserCredentials): Single<User>
}