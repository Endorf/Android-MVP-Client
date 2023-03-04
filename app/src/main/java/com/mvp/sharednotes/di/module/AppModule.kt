package com.mvp.sharednotes.di.module

import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.mvp.sharednotes.di.RoutingComponent
import dagger.Module
import dagger.Provides

@Module(subcomponents = [RoutingComponent::class])
class AppModule {

    @Provides
    fun provideIM(context: Context): InputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
}
