package com.masinerija.knowledge.view.fragments.onboardingfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.masinerija.knowledge.databinding.FragmentThirdOnboardingBinding
import com.masinerija.knowledge.view.MainActivity

class ThirdOnboardingFragment : Fragment() {

    private lateinit var binding: FragmentThirdOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdOnboardingBinding.inflate(layoutInflater, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    private fun setupListener() {
        binding.txtStart.setOnClickListener {
            startActivity(MainActivity.newIntent(requireActivity()))
            requireActivity().finish()
        }
    }
}
