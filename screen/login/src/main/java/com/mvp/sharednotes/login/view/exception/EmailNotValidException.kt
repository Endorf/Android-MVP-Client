package com.mvp.sharednotes.login.view.exception

class EmailNotValidException : RuntimeException(MESSAGE) {

    companion object {
        private const val MESSAGE = "Email is not valid"
    }
}