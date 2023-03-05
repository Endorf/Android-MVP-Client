package com.mvp.sharednotes.data.repository

import com.mvp.sharednotes.data.repository.storage.UserDataStore
import com.mvp.sharednotes.data.repository.storage.preferences.UserEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RoutingUserRepositoryImpl @Inject constructor(
    private val dataStore: UserDataStore
) : RoutingUserRepository {

    override fun get() = dataStore.get()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    override fun create(user: UserEntity) = dataStore.create(user)
        .ignoreElement()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}