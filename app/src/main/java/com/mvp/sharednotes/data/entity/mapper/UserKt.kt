package com.mvp.sharednotes.data.entity.mapper

import com.mvp.sharednotes.data.entity.User
import com.mvp.sharednotes.login.view.entity.UserCredentials

fun User.toLoginUser() = com.mvp.sharednotes.login.view.entity.User(email, name, userName)

fun com.mvp.sharednotes.login.view.entity.User.toUser() = User(email, name, userName)

fun UserCredentials.toUser() = User(email)