package com.mvp.sharednotes.login.di.module

import com.mvp.sharednotes.login.di.scope.LoginScope
import com.mvp.sharednotes.login.domain.LoginInteractor
import com.mvp.sharednotes.login.domain.LoginInteractorImpl
import com.mvp.sharednotes.login.view.Presenter
import com.mvp.sharednotes.login.view.PresenterImpl
import dagger.Binds
import dagger.Module

@Module
interface LoginModule {

    @Binds
    @LoginScope
    fun bindPresenter(presenter: PresenterImpl): Presenter

    @Binds
    @LoginScope
    fun bindInteractor(presenter: LoginInteractorImpl): LoginInteractor
}