package com.mvp.sharednotes

import android.app.Application
import com.mvp.sharednotes.di.AppComponent
import com.mvp.sharednotes.di.DaggerAppComponent
import com.mvp.sharednotes.login.di.DaggerLoginComponent
import com.mvp.sharednotes.login.di.LoginComponent
import com.mvp.sharednotes.login.di.LoginComponentProvider

class SharedNotesApplication : Application(), LoginComponentProvider {

    lateinit var appComponent: AppComponent
    lateinit var loginComponent: LoginComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()

        loginComponent = DaggerLoginComponent.builder()
            .component(appComponent)
            .build()
    }

    override fun provideLoginComponent(): LoginComponent = loginComponent
}