package com.mvp.sharednotes.login

import com.mvp.sharednotes.login.view.entity.User

interface LoginView {

    fun onLoadingStart()

    fun onLoadingFinish()

    fun userSignUpProcessing()

    fun onSuccessfulLogin(user: User)

    fun onError(e: Throwable)
}