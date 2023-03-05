package com.mvp.sharednotes.data.repository.storage.preferences

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder
import androidx.datastore.rxjava3.RxDataStore
import com.mvp.sharednotes.data.entity.User
import com.mvp.sharednotes.data.repository.storage.UserDataStore
import com.mvp.sharednotes.view.exception.UserNotExistsDataStoreException
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class SharedUserDataStore @Inject constructor(
    context: Context
) : UserDataStore {

    private var dataStore: RxDataStore<Preferences> =
        RxPreferenceDataStoreBuilder(context, STORAGE_NAME).build()

    override fun create(user: User): Single<User> =
        dataStore.updateDataAsync { preferences ->
            val mutablePreferences = preferences.toMutablePreferences().apply {
                set(email, user.email)
            }
            Single.just(mutablePreferences)
        }.map { user }

    override fun get(user: User): Single<User> = dataStore.data()
        .map { preferences ->
            parseUserEntity(preferences) {
                when {
                    !preferences.contains(email) -> UserNotExistsDataStoreException()
                    preferences[email]!! == user.email -> UserNotExistsDataStoreException()
                    else -> null
                }
            }
        }.firstOrError()

    override fun get(): Single<User> = dataStore.data()
        .map { preferences ->
            parseUserEntity(preferences) {
                when {
                    !preferences.contains(email) -> UserNotExistsDataStoreException()
                    else -> null
                }
            }
        }
        .firstOrError()

    override fun update(user: User): Single<User> =
        dataStore.updateDataAsync { preferences ->
            val mutablePreferences = preferences.toMutablePreferences().apply {
                set(email, user.email)
            }
            Single.just(mutablePreferences)
        }.map { user }

    private inline fun parseUserEntity(
        preferences: Preferences,
        validation: () -> Throwable?
    ): User {
        validation()?.let { throw it }

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