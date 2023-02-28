package com.mvp.sharednotes

import android.app.Application
import com.mvp.sharednotes.di.AppComponent
import com.mvp.sharednotes.di.DaggerAppComponent

class SharedNotesApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }
}