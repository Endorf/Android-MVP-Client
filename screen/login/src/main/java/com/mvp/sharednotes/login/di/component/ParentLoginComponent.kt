package com.mvp.sharednotes.login.di.component

import android.view.inputmethod.InputMethodManager
import com.mvp.sharednotes.login.domain.LoginRepository

interface ParentLoginComponent {

    fun getInputMethodManager(): InputMethodManager

    fun getLoginRepository(): LoginRepository
}