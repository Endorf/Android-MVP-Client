package com.mvp.sharednotes.di

import android.content.Context
import com.mvp.sharednotes.di.module.AppModule
import com.mvp.sharednotes.di.module.NetworkModule
import com.mvp.sharednotes.di.module.StorageModule
import com.mvp.sharednotes.di.scope.AppScope
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class, NetworkModule::class, StorageModule::class])
@AppScope
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}