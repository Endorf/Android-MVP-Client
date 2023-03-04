package com.mvp.sharednotes.di

import android.content.Context
import com.mvp.sharednotes.di.module.AppModule
import com.mvp.sharednotes.di.module.NetworkModule
import com.mvp.sharednotes.di.module.StorageModule
import com.mvp.sharednotes.di.scope.AppScope
import com.mvp.sharednotes.login.di.component.ParentLoginComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [AppModule::class, NetworkModule::class, StorageModule::class]
)
@AppScope
interface AppComponent : ParentLoginComponent {

    fun routingComponent(): RoutingComponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}