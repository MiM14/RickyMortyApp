package com.moaimar.ricknmortyapp.features.about.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.moaimar.ricknmortyapp.R
import com.moaimar.ricknmortyapp.databinding.FragmentAboutBinding

class AboutFragment: Fragment() {
    var binding : FragmentAboutBinding ? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(inflater)
        setUpView()
        return binding?.root
    }

    private fun setUpView() {
        binding?.apply {
            layoutToolbar.detailToolbar.apply {
                title = getString(R.string.about_title)
                setNavigationOnClickListener {
                    findNavController().navigateUp()
                }
            }
        }
    }
}