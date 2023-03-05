package com.mvp.sharednotes.data.repository.storage.db.entity.mapper

import com.mvp.sharednotes.data.entity.User
import com.mvp.sharednotes.data.repository.storage.db.entity.UserEntity

fun UserEntity.toUser() = User(email, name, userName)

fun User.toUserEntity() = UserEntity(0, email, name, userName)