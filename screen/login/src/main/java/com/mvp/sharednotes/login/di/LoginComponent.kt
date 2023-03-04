package com.mvp.sharednotes.login.di

import android.view.inputmethod.InputMethodManager
import com.mvp.sharednotes.login.LoginFragment
import com.mvp.sharednotes.login.ParentComponent
import com.mvp.sharednotes.login.di.module.LoginModule
import com.mvp.sharednotes.login.di.scope.FragmentScope
import dagger.Component

@Component(
    modules = [LoginModule::class],
    dependencies = [ParentComponent::class]
)
@FragmentScope
interface LoginComponent {

    fun inputMethod(): InputMethodManager

    @Component.Builder
    interface Builder {

        fun component(l: ParentComponent): Builder

        fun build(): LoginComponent
    }

    fun inject(fragment: LoginFragment)
}