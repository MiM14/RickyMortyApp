package com.moaimar.ricknmortyapp.app.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.moaimar.ricknmortyapp.R
import com.moaimar.ricknmortyapp.databinding.FragmentErrorHandlerBinding
import com.moaimar.ricknmortyapp.features.characterslist.presentation.CharacterDetailFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class ErrorFragment(): Fragment() {

    private var binding: FragmentErrorHandlerBinding? = null
    private val args : ErrorFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentErrorHandlerBinding.inflate(inflater)
        setUpErrorView()
        return binding?.root
    }



    protected fun setUpErrorView(){
        binding?.apply {
            errorTitle.text = args.title
            refreshButton.setOnClickListener {
                it.findNavController().navigate(ErrorFragmentDirections.toCharacterList())
            }
        }

    }

}