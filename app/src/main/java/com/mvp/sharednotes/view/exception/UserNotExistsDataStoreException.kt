package com.mvp.sharednotes.view.exception

class UserNotExistsDataStoreException : NullPointerException(MESSAGE) {

    companion object {
        private const val MESSAGE = "User doesn't exists!"
    }
}