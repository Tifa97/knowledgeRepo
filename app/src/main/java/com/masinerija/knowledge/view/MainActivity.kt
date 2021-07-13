package com.masinerija.knowledge.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.masinerija.knowledge.R
import com.masinerija.knowledge.databinding.ActivityMainBinding
import com.masinerija.knowledge.databinding.ActivityOnboardingBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnRetrofit.setOnClickListener{
            startActivity(BreweriesActivity.newIntent(this))
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
