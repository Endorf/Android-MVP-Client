package com.mvp.sharednotes

import android.app.Application
import com.mvp.sharednotes.di.AppComponent
import com.mvp.sharednotes.di.DaggerAppComponent
import com.mvp.sharednotes.login.di.component.LoginComponent
import com.mvp.sharednotes.login.di.LoginComponentProvider
import com.mvp.sharednotes.login.di.component.DaggerLoginComponent

class SharedNotesApplication : Application(), LoginComponentProvider {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }

    override fun provideLoginComponent(): LoginComponent.Builder = DaggerLoginComponent.builder()
        .component(appComponent)
}