package com.mvp.sharednotes.di.module

import com.mvp.sharednotes.data.domain.LoginInteractorImpl
import com.mvp.sharednotes.data.repository.LoginRepository
import com.mvp.sharednotes.data.repository.LoginRepositoryImpl
import com.mvp.sharednotes.login.domain.LoginInteractor
import dagger.Binds
import dagger.Module

@Module
interface LoginDomainModule {

    @Binds
    fun bindLoginInteractor(presenter: LoginInteractorImpl): LoginInteractor

    @Binds
    fun bindLoginRepository(presenter: LoginRepositoryImpl): LoginRepository
}