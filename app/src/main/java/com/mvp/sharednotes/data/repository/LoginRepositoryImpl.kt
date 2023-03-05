package com.mvp.sharednotes.data.repository

import com.mvp.sharednotes.data.entity.mapper.toLoginUser
import com.mvp.sharednotes.data.entity.mapper.toUser
import com.mvp.sharednotes.data.repository.storage.UserDataStore
import com.mvp.sharednotes.di.qualifier.Local
import com.mvp.sharednotes.di.qualifier.Remote
import com.mvp.sharednotes.di.qualifier.Shared
import com.mvp.sharednotes.login.view.entity.User
import com.mvp.sharednotes.login.view.entity.UserCredentials
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    @Local private val localDataStore: UserDataStore,
    @Remote private val remoteDataStore: UserDataStore,
    @Shared private val sharedDataStore: UserDataStore,
) : LoginRepository {

    override fun get(credentials: UserCredentials): Single<User> =
        remoteDataStore.get(
            credentials.toUser()
        ).doOnSuccess { user ->
            localDataStore.update(user).subscribe({}, {
                localDataStore.create(user).subscribe().dispose()
            }).dispose()
            sharedDataStore.update(user).subscribe().dispose()
        }.map {
            it.toLoginUser()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    @Suppress()
    override fun create(credentials: UserCredentials): Single<User> =
        remoteDataStore.create(
            credentials.toUser()
        ).doOnSuccess {
            localDataStore.create(it).subscribe().dispose()
            sharedDataStore.create(it).subscribe().dispose()
        }.map {
            User(it.email, it.name, it.userName)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}