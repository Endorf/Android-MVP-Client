package com.mvp.sharednotes.data.repository.storage.preferences

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.rxjava3.rxPreferencesDataStore
import androidx.datastore.rxjava3.RxDataStore
import com.mvp.sharednotes.data.entity.User
import com.mvp.sharednotes.data.repository.storage.UserDataStore
import com.mvp.sharednotes.view.exception.UserNotExistsDataStoreException
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class SharedUserDataStore @Inject constructor(
    private val context: Context
) : UserDataStore {

    private val Context.dataStore: RxDataStore<Preferences> by rxPreferencesDataStore(STORAGE_NAME)

    override fun create(user: User): Single<User> =
        context.dataStore.updateDataAsync { preferences ->
            preferences.toMutablePreferences().apply {
                set(email, user.email)
                user.name?.let { set(name, it) } ?: remove(name)
                user.userName?.let { set(username, it) } ?: remove(username)
            }
            return@updateDataAsync Single.just(preferences)
        }.map { user }

    // TODO: return specific user
    override fun get(user: User): Single<User> = TODO("Not yet implemented")

    override fun get(): Single<User> = context.dataStore.data()
        .map(::parseUserEntity)
        .firstOrError()

    private fun parseUserEntity(preferences: Preferences): User {
        if (!preferences.contains(email)) throw UserNotExistsDataStoreException()

        return User(
            preferences[email]!!,
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