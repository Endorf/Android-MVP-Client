package com.mvp.sharednotes.login.view

import android.util.Patterns
import com.mvp.sharednotes.login.LoginView
import com.mvp.sharednotes.login.domain.Interactor
import com.mvp.sharednotes.login.view.entity.UserCredentials
import com.mvp.sharednotes.login.view.exception.EmailNotValidException
import com.mvp.sharednotes.login.view.exception.NoInputException
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class PresenterImpl @Inject constructor(
    private val view: LoginView,
    private val interactor: Interactor
) : Presenter {

    private var disposable = CompositeDisposable()

    override fun login(credential: UserCredentials) {
        val validationStatus = validateUserInput(credential, view::onError)

        if (validationStatus) {
            view.onLoadingStart()
            interactor.login(credential).subscribe({
                view.onSuccessfulLogin(it)
                view.onLoadingFinish()
            }, {
                view.onError(it)
                view.onLoadingFinish()
            }).also {
                disposable.add(it)
            }
        }
    }

    override fun destroy() {
        disposable.dispose()
    }

    companion object {

        private fun isEmailValid(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        private inline fun validateUserInput(
            credential: UserCredentials,
            onError: (Throwable) -> Unit
        ): Boolean {
            return when {
                credential.email.isBlank() -> {
                    onError(NoInputException())
                    false
                }
                isEmailValid(credential.email).not() -> {
                    onError(EmailNotValidException())
                    false
                }
                else -> true
            }
        }
    }
}