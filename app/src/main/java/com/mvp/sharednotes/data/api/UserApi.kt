package com.mvp.sharednotes.data.api

import com.mvp.sharednotes.data.entity.UserEntity
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @GET("users/{id}")
    suspend fun get(@Path("id") id: Int): UserEntity

    @POST("user")
    @FormUrlEncoded
    suspend fun create(@Body user: UserEntity): UserEntity
}