package com.mvp.sharednotes.login.di.component

import com.mvp.sharednotes.login.LoginFragment
import com.mvp.sharednotes.login.LoginView
import com.mvp.sharednotes.login.di.module.LoginModule
import com.mvp.sharednotes.login.di.scope.LoginScope
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [LoginModule::class],
    dependencies = [ParentLoginComponent::class]
)
@LoginScope
interface LoginComponent {

    @Component.Builder
    interface Builder {

        fun component(l: ParentLoginComponent): Builder

        @BindsInstance
        fun view(l: LoginView): Builder

        fun build(): LoginComponent
    }

    fun inject(fragment: LoginFragment)
}