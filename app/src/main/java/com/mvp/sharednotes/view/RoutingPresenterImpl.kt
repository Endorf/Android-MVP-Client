package com.mvp.sharednotes.view

import com.mvp.sharednotes.RoutingView
import com.mvp.sharednotes.data.repository.RoutingUserRepository
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class RoutingPresenterImpl @Inject constructor(
    private val view: RoutingView,
    private val repository: RoutingUserRepository
) : RoutingPresenter {

    private var disposable: Disposable? = null

    override fun isUserSignedIn() {
        disposable = repository.get().ignoreElement().subscribe({
            view.onSuccessfulLogin()
        }, {
            view.onError(it)
        })
    }

    override fun destroy() {
        disposable?.dispose()
    }
}