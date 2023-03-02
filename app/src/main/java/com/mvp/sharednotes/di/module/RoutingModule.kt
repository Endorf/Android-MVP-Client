package com.mvp.sharednotes.di.module

import com.mvp.sharednotes.view.RoutingPresenter
import com.mvp.sharednotes.data.repository.RoutingUserRepository
import com.mvp.sharednotes.di.scope.ActivityScope
import com.mvp.sharednotes.view.RoutingPresenterImpl
import com.mvp.sharednotes.data.repository.RoutingUserRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RoutingModule {

    @Binds
    @ActivityScope
    fun bindPresenter(presenter: RoutingPresenterImpl): RoutingPresenter

    @Binds
    @ActivityScope
    fun bindRepository(presenter: RoutingUserRepositoryImpl): RoutingUserRepository
}