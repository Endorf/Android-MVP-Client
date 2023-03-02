package com.mvp.sharednotes.data.repository.storage.cloud.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserEntity(
    val id: Int? = null,
    var email: String? = null,
    val name: String? = null,
    val userName: String? = null
) : Parcelable