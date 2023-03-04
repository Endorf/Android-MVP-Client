package com.mvp.sharednotes.data.repository.storage.preferences

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.rxjava3.rxPreferencesDataStore
import androidx.datastore.rxjava3.RxDataStore
import com.mvp.sharednotes.view.exception.UserNotExistsDataStoreException
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class UserInfoDataStoreImpl @Inject constructor(
    private val context: Context
) : UserInfoDataStore {

    private val Context.dataStore: RxDataStore<Preferences> by rxPreferencesDataStore(STORAGE_NAME)

    override fun create(user: UserEntity): Single<UserEntity> =
        context.dataStore.updateDataAsync { preferences ->
            preferences.toMutablePreferences().apply {
                user.email?.let { set(email, it) } ?: remove(email)
                user.name?.let { set(name, it) } ?: remove(name)
                user.userName?.let { set(username, it) } ?: remove(username)
            }
            return@updateDataAsync Single.just(preferences)
        }
            .map { user }

    override fun get(): Single<UserEntity> = context.dataStore.data()
        .map(::parseUserEntity)
        .firstOrError()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    private fun parseUserEntity(preferences: Preferences): UserEntity {
        if (!preferences.contains(email)) throw UserNotExistsDataStoreException()

        return UserEntity(
            preferences[email],
            preferences[name],
            preferences[username]
        )
    }

    private companion object {
        const val STORAGE_NAME = "user_info_store"
        const val EMAIL = ""
        const val NAME = ""
        const val USERNAME = ""

        val email = stringPreferencesKey(EMAIL)
        val name = stringPreferencesKey(NAME)
        val username = stringPreferencesKey(USERNAME)
    }
}