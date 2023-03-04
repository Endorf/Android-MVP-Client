package com.mvp.sharednotes.data.repository

import com.mvp.sharednotes.data.repository.storage.preferences.UserEntity
import com.mvp.sharednotes.data.repository.storage.preferences.UserInfoDataStore
import com.mvp.sharednotes.login.domain.LoginRepository
import com.mvp.sharednotes.login.view.entity.User
import com.mvp.sharednotes.login.view.entity.UserCredentials
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataStore: UserInfoDataStore
) : LoginRepository {

    override fun get(user: UserCredentials): Single<User> {
        return dataStore.get()
            .map {
                // fixme make UserEntity not Nullable
                User(it.email!!, it.name, it.userName)
            }
    }

    override fun create(user: UserCredentials): Single<User> {
        val userEntity = UserEntity(user.email)
        return dataStore.create(userEntity).map {
            // fixme make UserEntity not Nullable
//            User(it.email!!, it.name, it.userName)
            MOCK
        }
    }

    companion object {
        private val MOCK = User("email", "name", "username")
    }
}