package com.mvp.sharednotes.login.view

import com.mvp.sharednotes.login.view.entity.UserCredentials

interface Presenter {

    fun login(credential: UserCredentials)

    fun destroy()
}