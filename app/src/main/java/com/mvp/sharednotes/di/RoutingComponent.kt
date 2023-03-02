package com.mvp.sharednotes.di

import com.mvp.sharednotes.RoutingActivity
import com.mvp.sharednotes.di.module.RoutingModule
import com.mvp.sharednotes.di.scope.ActivityScope
import com.mvp.sharednotes.RoutingView
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [RoutingModule::class])
@ActivityScope
interface RoutingComponent {

    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun setView(v: RoutingView): Builder

        fun build(): RoutingComponent
    }

    fun inject(activity: RoutingActivity)
}