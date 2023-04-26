package com.moaimar.ricknmortyapp.features.locations.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.moaimar.ricknmortyapp.R
import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.app.error.ErrorAppHandler
import com.moaimar.ricknmortyapp.databinding.FragmentLocationsListBinding
import com.moaimar.ricknmortyapp.features.locations.presentation.adapters.LocationsAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LocationsListFragment() : Fragment() {
    @Inject
    lateinit var errorAppHandler: ErrorAppHandler

    private var binding: FragmentLocationsListBinding? = null
    private val viewModel by viewModels<LocationsListViewModel>()

    @Inject
    lateinit var locationsFeedAdapter: LocationsAdapter

    private var skeleton: Skeleton? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocationsListBinding.inflate(inflater)
        setUpView()
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        viewModel.getLocations()
    }

    private fun setUpView() {
        binding?.apply {
            fragmentLocationsList.apply {
                adapter = locationsFeedAdapter
                layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.VERTICAL, false
                )

                skeleton = applySkeleton(R.layout.item_locations_list, 8)

                swipeRefreshLayout.setOnRefreshListener {
                    viewModel.refreshFeed()
                }

                locationsFeedAdapter.setOnClickItem { keyId ->
                    findNavController().navigate(
                        LocationsListFragmentDirections.actionLocationListToLocationDetail(
                            keyId
                        )
                    )
                }

                toolbar.sectionToolbar.apply {
                    title = getString(R.string.locations_title)
                    val menuItem = menu.findItem(R.id.search_bar)
                    val search = menuItem.actionView as SearchView
                    search.maxWidth = Int.MAX_VALUE
                    search.queryHint = getString(R.string.search_hint)

                    search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            viewModel.searchLocationByKeyWord(query ?: "")
                            return false
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            if (newText == "") {
                                viewModel.getLocations()
                            }
                            return false
                        }

                    })
                }
            }
        }
    }

    private fun setUpObservers() {
        val locationsObserver =
            Observer<LocationsListViewModel.UiState> { uiState ->
                if (uiState.isLoading) {
                    skeleton?.showSkeleton()
                } else {
                    skeleton?.showOriginal()
                    binding?.swipeRefreshLayout?.isRefreshing = false
                    if (uiState.error != null) {
                        errorHandler(uiState.error)
                    } else {
                        locationsFeedAdapter.submitList(uiState.locations)
                    }
                }
            }
        viewModel.uiState.observe(viewLifecycleOwner, locationsObserver)
    }

    private fun errorHandler(error: ErrorApp) {
        errorAppHandler.navigateToError(error)
    }
}