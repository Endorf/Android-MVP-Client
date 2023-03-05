package com.mvp.sharednotes.data.repository.storage.cloud.entity

import com.mvp.sharednotes.data.entity.User

fun UserEntity.toUser() = User(email, name, userName)

fun User.toUserEntity() = UserEntity(email = email, name = name, userName = userName)