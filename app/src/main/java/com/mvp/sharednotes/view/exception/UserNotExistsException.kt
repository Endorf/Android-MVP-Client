package com.mvp.sharednotes.view.exception

class UserNotExistsException : NullPointerException(MESSAGE) {

    companion object {
        private const val MESSAGE = "User doesn't exists!"
    }
}