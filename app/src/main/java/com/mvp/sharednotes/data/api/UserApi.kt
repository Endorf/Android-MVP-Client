package com.mvp.sharednotes.data.api

import com.mvp.sharednotes.data.entity.UserEntity
import retrofit2.http.*

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