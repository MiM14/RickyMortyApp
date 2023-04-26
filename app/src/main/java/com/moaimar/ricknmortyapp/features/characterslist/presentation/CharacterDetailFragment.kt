package com.moaimar.ricknmortyapp.features.characterslist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.faltenreich.skeletonlayout.Skeleton
import com.moaimar.ricknmortyapp.R
import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.app.error.ErrorAppHandler
import com.moaimar.ricknmortyapp.app.extensions.loadUrl
import com.moaimar.ricknmortyapp.databinding.FragmentCharacterDetailBinding
import com.moaimar.ricknmortyapp.features.characterslist.domain.CharacterInfo
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    @Inject
    lateinit var errorAppHandler: ErrorAppHandler

    private var binding: FragmentCharacterDetailBinding? = null
    private val viewModel by viewModels<CharacterDetailViewModel>()
    private val args: CharacterDetailFragmentArgs by navArgs()
    private var skeleton: Skeleton? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailBinding.inflate(inflater)
        skeleton = binding?.skeletonLayout
        setUpView()
        return binding?.root
    }

    private fun setUpView() {
        binding?.apply {
            layoutToolbar.detailToolbar.apply {
                title = getString(R.string.character_detail_title)
                setNavigationOnClickListener {
                    findNavController().navigateUp()
                }
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        viewModel.getCharacterDetail(args.characterId)
    }

    private fun setUpObservers() {
        val characterDetailObserver =
            Observer<CharacterDetailViewModel.UiState> { uiState ->
                if (uiState.isLoading) {
                    skeleton?.showSkeleton()
                } else {
                    skeleton?.showOriginal()
                    if (uiState.error != null) {
                        errorHandler(uiState.error)
                    } else {
                        bind(uiState.character)
                    }
                }
            }
        viewModel.uiState.observe(viewLifecycleOwner, characterDetailObserver)
    }

    private fun bind(character: CharacterInfo?) {
        binding?.apply {
            character?.let {
                image.loadUrl(it.urlImage)
                title.text = it.name
                species.text = it.species
                status.text = it.status
                gender.text = it.gender
            }
        }
    }

    private fun errorHandler(error: ErrorApp) {
        errorAppHandler.navigateToError(error)
    }
}