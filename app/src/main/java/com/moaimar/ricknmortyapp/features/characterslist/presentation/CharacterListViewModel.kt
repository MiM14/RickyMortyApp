package com.moaimar.ricknmortyapp.features.characterslist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.features.characterslist.domain.CharactersFeed
import com.moaimar.ricknmortyapp.features.characterslist.domain.GetFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(private val getFeedUseCase: GetFeedUseCase) :
    ViewModel() {

    private val _uiState: MutableLiveData<UiState> = MutableLiveData()
    val uiState: LiveData<UiState> = _uiState

    fun getCharactersList() {
        viewModelScope.launch(Dispatchers.IO) {
            getFeedUseCase.invoke().fold({ error ->
                _uiState.postValue(
                    UiState(isLoading = false, error = error)
                )
            }, { characterList ->
                _uiState.postValue(
                    UiState(isLoading = false, characters = characterList)
                )
            })

        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val characters: List<CharactersFeed>? = null,
        val error: ErrorApp? = null
    )
}