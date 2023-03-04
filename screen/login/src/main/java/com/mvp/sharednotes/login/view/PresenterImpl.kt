package com.mvp.sharednotes.login.view

import com.mvp.sharednotes.login.view.entity.UserCredentials
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class PresenterImpl @Inject constructor(
) : Presenter {

    private var disposable = CompositeDisposable()

    override fun login(credential: UserCredentials) {
    }

    override fun destroy() {
        disposable.dispose()
    }
}