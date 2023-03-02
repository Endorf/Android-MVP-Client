package com.mvp.sharednotes.data.repository

import com.mvp.sharednotes.data.repository.storage.preferences.UserEntity
import com.mvp.sharednotes.data.repository.storage.preferences.UserInfoDataStore
import javax.inject.Inject

class RoutingUserRepositoryImpl @Inject constructor(
    private val dataStore: UserInfoDataStore
) : RoutingUserRepository {

    override fun get() = dataStore.get()

    override fun create(user: UserEntity) = dataStore.create(user)
}