package com.mvp.sharednotes

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
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
    private var keepOnScreenCondition: Boolean = true

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

    override fun onSuccessfulLogin() {
        keepOnScreenCondition = false
        // TODO("implement notes module")
    }

    override fun onError(e: Throwable) {
        keepOnScreenCondition = false
        findNavController(R.id.nav_host_fragment_content_main)
    }

    private fun initSplashScreen() = installSplashScreen().apply {
        setKeepOnScreenCondition { keepOnScreenCondition }

        setOnExitAnimationListener {
            val fadeAnim = ObjectAnimator.ofFloat(
                it.view,
                View.ALPHA,
                1f,
                0f
            )
            fadeAnim.duration = ANIMATION_DURATION
            fadeAnim.interpolator = AccelerateInterpolator()
            fadeAnim.start()
        }
    }

    companion object {
        private const val ANIMATION_DURATION = 300L
    }
}