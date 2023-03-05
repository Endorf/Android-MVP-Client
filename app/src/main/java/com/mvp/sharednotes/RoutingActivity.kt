package com.mvp.sharednotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.mvp.sharednotes.databinding.ActivityRoutingBinding
import com.mvp.sharednotes.view.RoutingPresenter
import javax.inject.Inject

class RoutingActivity : AppCompatActivity(), RoutingView {

    private lateinit var binding: ActivityRoutingBinding
    private lateinit var splashScreen: SplashScreen

    @Inject
    lateinit var presenter: RoutingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        (applicationContext as SharedNotesApplication).appComponent
            .routingComponent()
            .setView(this)
            .build()
            .inject(this)

        splashScreen = initSplashScreen()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_routing)

        presenter.isUserSignedIn()
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    private fun initSplashScreen() = installSplashScreen().apply {
        setKeepOnScreenCondition { true }
    }

    override fun onSuccessfulLogin() {
        splashScreen.setKeepOnScreenCondition { false }
        TODO("implement notes module")
    }

    override fun onError(e: Throwable) {
        splashScreen.setKeepOnScreenCondition { false }
        findNavController(R.id.nav_host_fragment_content_main)
    }
}