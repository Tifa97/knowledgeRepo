package com.masinerija.knowledge.view.fragments.onboardingfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.masinerija.knowledge.databinding.FragmentFirstOnboardingBinding

class FirstOnboardingFragment : Fragment() {

    private lateinit var binding: FragmentFirstOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstOnboardingBinding.inflate(layoutInflater, null, false)
        return binding.root
    }
}
