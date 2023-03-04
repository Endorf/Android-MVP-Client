package com.mvp.sharednotes.login.view

import com.mvp.sharednotes.login.LoginView
import com.mvp.sharednotes.login.view.entity.UserCredentials
import io.reactivex.rxjava3.disposables.CompositeDisposable

class PresenterImpl(
    private val view: LoginView
) : Presenter {

    private var disposable = CompositeDisposable()

    override fun login(credential: UserCredentials) {
        view.onLoadingStart()
        view.onSuccessfulLogin()
        view.onLoadingFinish()
    }

    override fun destroy() {
        disposable.dispose()
    }
}