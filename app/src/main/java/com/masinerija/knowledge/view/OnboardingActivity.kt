package com.masinerija.knowledge.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.masinerija.knowledge.R
import com.masinerija.knowledge.databinding.ActivityOnboardingBinding
import com.masinerija.knowledge.view.adapter.OnboardingAdapter
import com.masinerija.knowledge.view.fragments.onboardingfragments.FirstOnboardingFragment
import com.masinerija.knowledge.view.fragments.onboardingfragments.SecondOnboardingFragment
import com.masinerija.knowledge.view.fragments.onboardingfragments.ThirdOnboardingFragment

class OnboardingActivity : AppCompatActivity() {

    private val binding by lazy { ActivityOnboardingBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initializeIndicator()
        populatePagerAdapter()
        setupClickListeners()
    }

    private fun initializeIndicator() {
        binding.introIndicator0.setBackgroundResource(R.drawable.indicator_selected)
    }

    private fun populatePagerAdapter() {
        val adapter = OnboardingAdapter(supportFragmentManager)
        binding.viewPager.adapter = adapter
        adapter.addFragment(FirstOnboardingFragment(), "")
        adapter.addFragment(SecondOnboardingFragment(), "")
        adapter.addFragment(ThirdOnboardingFragment(), "")
        adapter.notifyDataSetChanged()
        binding.viewPager.addOnPageChangeListener(
            object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {
                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    updateIndicators(position)
                }

                override fun onPageSelected(position: Int) {
                    if (position == 2) {
                        binding.txtSkipOnboarding.visibility = View.INVISIBLE
                        binding.txtSkipOnboarding.isEnabled = false
                    } else if (binding.txtSkipOnboarding.visibility == View.INVISIBLE) {
                        binding.txtSkipOnboarding.visibility = View.VISIBLE
                        binding.txtSkipOnboarding.isEnabled = true
                    }
                }
            }
        )
    }

    private fun updateIndicators(position: Int) {
        val indicators = buildIndicators()
        for (i in 0 until indicators.count()) {
            indicators[i].setBackgroundResource(
                if (i == position) R.drawable.indicator_selected else R.drawable.indicator_unselected
            )
        }
    }

    private fun buildIndicators(): ArrayList<ImageView> {
        val indicators = arrayListOf<ImageView>()
        indicators.add(binding.introIndicator0)
        indicators.add(binding.introIndicator1)
        indicators.add(binding.introIndicator2)
        return indicators
    }

    private fun setupClickListeners() {
        binding.txtSkipOnboarding.setOnClickListener {
            startActivity(MainActivity.newIntent(this))
            finish()
        }
    }

    companion object{
        fun newIntent(context: Context) = Intent(context, OnboardingActivity::class.java)
    }
}
