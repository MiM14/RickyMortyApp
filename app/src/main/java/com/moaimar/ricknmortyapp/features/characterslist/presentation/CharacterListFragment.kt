package com.moaimar.ricknmortyapp.features.characterslist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.moaimar.ricknmortyapp.databinding.FragmentCharacterListBinding
import com.moaimar.ricknmortyapp.features.characterslist.presentation.adapter.CharacterFeedAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterListFragment() : Fragment() {

    private var binding: FragmentCharacterListBinding? = null
    private val viewModel by viewModels<CharacterListViewModel>()

    @Inject
    lateinit var characterFeedAdapter: CharacterFeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterListBinding.inflate(inflater)
        setUpView()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
        viewModel.getCharactersList()
    }


    private fun setUpView() {
        binding?.apply {
            fragmentCharacterList.apply {
                adapter = characterFeedAdapter
                layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.VERTICAL, false
                )
            }
            characterFeedAdapter.setOnClickedItem { keyId ->
                findNavController().navigate(
                    CharacterListFragmentDirections.actionListToDetail(
                        keyId
                    )
                )
            }
        }
    }

    private fun setUpObserver() {
        val characterListObserver =
            Observer<CharacterListViewModel.UiState> { uiState ->
                if (uiState.isLoading) {
                    //TODO
                } else {
                    if (uiState.error != null) {
                        //TODO
                    } else {
                        characterFeedAdapter.submitList(uiState.characters)
                    }
                }
            }
        viewModel.uiState.observe(viewLifecycleOwner, characterListObserver)
    }

}