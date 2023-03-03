package com.mvp.sharednotes.login

interface LoginView {

    fun onLoadingStart()

    fun onLoadingFinish()

    fun userSignUpProcessing()

    fun onSuccessfulLogin()

    fun onError(e: Throwable)
}