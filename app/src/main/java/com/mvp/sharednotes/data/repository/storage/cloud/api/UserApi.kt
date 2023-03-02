package com.mvp.sharednotes.data.repository.storage.cloud.api

import com.mvp.sharednotes.data.repository.storage.cloud.entity.UserEntity
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @GET(GET_ENDPOINT)
    suspend fun get(@Path(ID) id: Int): UserEntity

    @POST(POST_ENDPOINT)
    @FormUrlEncoded
    @Headers(CONTENT_TYPE_APPLICATION_JSON)
    suspend fun create(@Body user: UserEntity): UserEntity

    private companion object {
        const val ID = "id"
        const val GET_ENDPOINT = "users/{$ID}"
        const val POST_ENDPOINT = "user"
        const val CONTENT_TYPE_APPLICATION_JSON = "Content-Type: application/json"
    }
}