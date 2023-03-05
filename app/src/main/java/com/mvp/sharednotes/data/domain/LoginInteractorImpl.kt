package com.mvp.sharednotes.data.domain

import com.mvp.sharednotes.data.repository.LoginRepository
import com.mvp.sharednotes.login.domain.LoginInteractor
import com.mvp.sharednotes.login.view.entity.User
import com.mvp.sharednotes.login.view.entity.UserCredentials
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginInteractorImpl @Inject constructor(
    private val repository: LoginRepository
) : LoginInteractor {

    override fun login(credentials: UserCredentials): Single<User> {
        return repository.get(credentials)
            .onErrorResumeNext {
                repository.create(credentials)
            }
    }
}