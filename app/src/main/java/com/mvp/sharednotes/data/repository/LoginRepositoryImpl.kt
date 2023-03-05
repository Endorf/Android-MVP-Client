package com.mvp.sharednotes.data.repository

import com.mvp.sharednotes.data.repository.storage.UserDataStore
import com.mvp.sharednotes.di.qualifier.Local
import com.mvp.sharednotes.login.view.entity.User
import com.mvp.sharednotes.login.view.entity.UserCredentials
import com.mvp.sharednotes.view.exception.UserNotExistsDataStoreException
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

typealias LocalUser = com.mvp.sharednotes.data.entity.User

class LoginRepositoryImpl @Inject constructor(
    @Local private val dataStore: UserDataStore,
) : LoginRepository {

    override fun get(credentials: UserCredentials): Single<User> {
        val userEntity = LocalUser(credentials.email)

        return dataStore.get(userEntity)
            .map {
                User(it.email, it.name, it.userName)
            }.onErrorResumeNext {
                when (it) {
                    is UserNotExistsDataStoreException -> Single.just(MOCK)//Try to get data from BD
                    else -> Single.error(it)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    @Suppress()
    override fun create(credentials: UserCredentials): Single<User> {
        val userEntity = LocalUser(credentials.email)

        return dataStore.create(userEntity).map {
            // fixme make UserEntity not Nullable
//            User(it.email!!, it.name, it.userName)
            MOCK
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    companion object {
        private val MOCK = User("email", "name", "username")
    }
}