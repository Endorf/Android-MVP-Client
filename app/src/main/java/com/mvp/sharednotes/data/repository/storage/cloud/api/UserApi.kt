package com.mvp.sharednotes.data.repository.storage.cloud.api

import com.mvp.sharednotes.data.repository.storage.cloud.entity.UserEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Headers
import retrofit2.http.Body
import retrofit2.http.Path

interface UserApi {

    @POST(POST_ENDPOINT)
    @Headers(CONTENT_TYPE_APPLICATION_JSON)
    fun create(@Body user: UserEntity): Single<UserEntity>

    @GET(GET_ENDPOINT)
    @Headers(CONTENT_TYPE_APPLICATION_JSON)
    fun get(@Path(ID) id: Int): Single<UserEntity>

    @PATCH(PATH_ENDPOINT)
    @Headers(CONTENT_TYPE_APPLICATION_JSON)
    fun update(@Path("id") id: Int, @Body user: UserEntity): Single<UserEntity>

    private companion object {
        const val ID = "id"
        const val GET_ENDPOINT = "users/{$ID}"
        const val PATH_ENDPOINT = "users/{$ID}"
        const val POST_ENDPOINT = "user"
        const val CONTENT_TYPE_APPLICATION_JSON = "Content-Type: application/json"
    }
}