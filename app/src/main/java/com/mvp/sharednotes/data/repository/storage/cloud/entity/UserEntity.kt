package com.mvp.sharednotes.data.repository.storage.cloud.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserEntity(
    val id: Int? = null,
    var email: String,
    val name: String? = null,
    @SerializedName("username") val userName: String? = null
) : Parcelable