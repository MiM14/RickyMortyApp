package com.moaimar.ricknmortyapp.app.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moaimar.ricknmortyapp.databinding.FragmentErrorHandlerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ErrorFragment(): Fragment() {

    private var binding: FragmentErrorHandlerBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentErrorHandlerBinding.inflate(inflater)
        return binding?.root
    }

}