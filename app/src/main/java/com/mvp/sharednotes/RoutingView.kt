package com.mvp.sharednotes

interface RoutingView {

    fun onSuccessfulLogin()

    fun onError(e: Throwable)
}