package com.mvp.sharednotes.login.di

import com.mvp.sharednotes.login.di.component.LoginComponent

interface LoginComponentProvider {

    fun provideLoginComponent(): LoginComponent.Builder
}