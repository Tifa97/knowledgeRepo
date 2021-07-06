package com.masinerija.knowledge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.masinerija.knowledge.R
import com.masinerija.knowledge.databinding.ActivitySplashBinding
import com.masinerija.knowledge.viewmodel.SplashViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }
    private val viewModel: SplashViewModel by viewModel()
    private var isInitialRun = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        collectRun()
        animateSplash()
    }

    private fun collectRun() {
        lifecycleScope.launch{
            viewModel.getIsInitialRun().collectLatest {
                isInitialRun = it
            }
        }
    }

    private fun animateSplash() {
        binding.txtWelcome.alpha = 0f
        binding.txtWelcome.animate().alpha(1f).setDuration(1500).withEndAction {
            if (isInitialRun) {
                startActivity(
                    OnboardingActivity.newIntent(
                        this@SplashActivity
                    )
                )
                viewModel.setIsInitialRun(false)
            } else {
                startActivity(
                    MainActivity.newIntent(
                        this@SplashActivity
                    )
                )
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }.start()
    }
}