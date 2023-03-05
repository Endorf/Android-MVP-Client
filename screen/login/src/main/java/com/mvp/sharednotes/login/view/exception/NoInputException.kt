package com.mvp.sharednotes.login.view.exception

class NoInputException : RuntimeException(MESSAGE) {

    companion object {
        private const val MESSAGE = "Field is empty"
    }
}