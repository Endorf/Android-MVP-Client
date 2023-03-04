package com.mvp.sharednotes.login.di.module

import com.mvp.sharednotes.login.di.scope.FragmentScope
import com.mvp.sharednotes.login.view.Presenter
import com.mvp.sharednotes.login.view.PresenterImpl
import dagger.Binds
import dagger.Module

@Module
interface LoginModule {

    @Binds
    @FragmentScope
    fun bindPresenter(presenter: PresenterImpl): Presenter
}