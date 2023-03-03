package com.mvp.sharednotes.login.view.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val email: String,
    val name: String?,
    val userName: String?
) : Parcelable