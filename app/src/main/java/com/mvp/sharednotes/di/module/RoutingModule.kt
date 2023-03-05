package com.mvp.sharednotes.di.module

import com.mvp.sharednotes.data.repository.RoutingUserRepository
import com.mvp.sharednotes.data.repository.RoutingUserRepositoryImpl
import com.mvp.sharednotes.view.RoutingPresenter
import com.mvp.sharednotes.di.scope.RoutingScope
import com.mvp.sharednotes.view.RoutingPresenterImpl
import dagger.Binds
import dagger.Module

@Module
interface RoutingModule {

    @Binds
    @RoutingScope
    fun bindPresenter(presenter: RoutingPresenterImpl): RoutingPresenter

    @Binds
    @RoutingScope
    fun bindRepository(presenter: RoutingUserRepositoryImpl): RoutingUserRepository
}