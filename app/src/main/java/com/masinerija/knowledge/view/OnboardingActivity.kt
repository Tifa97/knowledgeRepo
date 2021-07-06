package com.masinerija.knowledge.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.masinerija.knowledge.R

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
    }

    companion object{
        fun newIntent(context: Context) = Intent(context, OnboardingActivity::class.java)
    }
}
