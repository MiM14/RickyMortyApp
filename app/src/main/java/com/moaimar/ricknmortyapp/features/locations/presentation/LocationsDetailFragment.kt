package com.moaimar.ricknmortyapp.features.locations.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.moaimar.ricknmortyapp.R
import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.app.error.ErrorAppHandler
import com.moaimar.ricknmortyapp.databinding.FragmentLocationsDetailBinding
import com.moaimar.ricknmortyapp.features.locations.presentation.adapters.ResidentsAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LocationsDetailFragment : Fragment() {
    @Inject
    lateinit var errorAppHandler: ErrorAppHandler

    @Inject
    lateinit var residentAdapter: ResidentsAdapter

    private var binding: FragmentLocationsDetailBinding? = null
    private val viewModel by viewModels<LocationsDetailViewModel>()
    private val args: LocationsDetailFragmentArgs by navArgs()
    private var skeleton: Skeleton? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocationsDetailBinding.inflate(inflater)
        setUpView()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        viewModel.getLocationDetail(args.locationId)
    }

    private fun setUpView() {
        binding?.apply {
            layoutToolbar.detailToolbar.apply {
                title = getString(R.string.location_detail_title)
                setNavigationOnClickListener {
                    findNavController().navigateUp()
                }
            }
            fragmentResidentsList.apply {
                adapter = residentAdapter
                layoutManager = GridLayoutManager(
                    requireContext(), 3
                )

                skeleton = applySkeleton(R.layout.item_locations_detail, 3)
                residentAdapter.setOnClickItem { keyId ->
                    findNavController().navigate(
                        LocationsDetailFragmentDirections.actionLocationDetailToCharacterDetail(
                            keyId
                        )
                    )
                }
            }
        }
    }

    private fun setUpObservers() {
        val residentsObserver =
            Observer<LocationsDetailViewModel.UiState> { uiState ->
                if (uiState.isLoading) {
                    skeleton?.showSkeleton()
                } else {
                    skeleton?.showOriginal()
                    if (uiState.error != null) {
                        errorHandler(uiState.error)
                    } else {
                        binding?.apply {
                            layoutToolbar.detailToolbar.title = uiState.location?.name
                        }
                        residentAdapter.submitList(uiState.location?.residents)
                    }
                }
            }
        viewModel.uiState.observe(viewLifecycleOwner, residentsObserver)
    }

    private fun errorHandler(error: ErrorApp) {
        errorAppHandler.navigateToError(error)
    }
}