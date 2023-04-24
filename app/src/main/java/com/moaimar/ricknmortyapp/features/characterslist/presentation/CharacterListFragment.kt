package com.moaimar.ricknmortyapp.features.characterslist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.moaimar.ricknmortyapp.R
import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.app.error.ErrorAppHandler
import com.moaimar.ricknmortyapp.databinding.FragmentCharacterListBinding
import com.moaimar.ricknmortyapp.features.characterslist.presentation.adapter.CharacterFeedAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterListFragment() : Fragment() {


    @Inject
    lateinit var errorAppHandler: ErrorAppHandler

    private var binding: FragmentCharacterListBinding? = null
    private val viewModel by viewModels<CharacterListViewModel>()

    @Inject
    lateinit var characterFeedAdapter: CharacterFeedAdapter

    private var skeleton: Skeleton? = null

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
                skeleton = applySkeleton(R.layout.item_character_list, 4)
                swipeRefreshLayout.setOnRefreshListener {
                    viewModel.refreshFeed()
                }


            }
            characterFeedAdapter.setOnClickedItem { keyId ->
                findNavController().navigate(
                    CharacterListFragmentDirections.actionListToDetail(
                        keyId
                    )
                )
            }
            toolbar.sectionToolbar.apply {
                title = getString(R.string.character_title)
                val menuItem = menu.findItem(R.id.search_bar)
                val search: SearchView = menuItem.actionView as SearchView
                search.maxWidth = Int.MAX_VALUE
                search.queryHint = "Search here!"

                search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        viewModel.searchCharactersByKeyword(query ?: "")
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        if(newText == ""){
                            viewModel.getCharactersList()
                        }
                        return false
                    }

                })
            }
        }
    }

    private fun setUpObserver() {
        val characterListObserver =
            Observer<CharacterListViewModel.UiState> { uiState ->
                if (uiState.isLoading) {
                    skeleton?.showSkeleton()
                } else {
                    skeleton?.showOriginal()
                    binding?.swipeRefreshLayout?.isRefreshing = false
                    if (uiState.error != null) {
                        errorHandler(uiState.error)
                    } else {
                        characterFeedAdapter.submitList(uiState.characters)
                    }
                }
            }

        viewModel.uiState.observe(viewLifecycleOwner, characterListObserver)
    }
    private fun errorHandler(error : ErrorApp){
        errorAppHandler.navigateToError(error)
    }
}