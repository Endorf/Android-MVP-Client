package com.mvp.sharednotes.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val email: String,
    val name: String? = null,
    val userName: String? = null
) : Parcelable
