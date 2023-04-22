package com.moaimar.ricknmortyapp.features.characterslist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.features.characterslist.domain.CharactersFeed
import com.moaimar.ricknmortyapp.features.characterslist.domain.GetFeedUseCase
import com.moaimar.ricknmortyapp.features.characterslist.domain.SearchCharactersByKeywordUseCase
import com.moaimar.ricknmortyapp.features.characterslist.domain.RefreshUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getFeedUseCase: GetFeedUseCase,
    private val refreshUseCase: RefreshUseCase,
    private val searchCharactersByKeywordUseCase: SearchCharactersByKeywordUseCase
) : ViewModel() {

    private val _uiState: MutableLiveData<UiState> = MutableLiveData()
    val uiState: LiveData<UiState> = _uiState

    fun getCharactersList() {
        _uiState.value = UiState(isLoading = true)
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

    fun refreshFeed() {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            refreshUseCase.invoke().fold({ error ->
                _uiState.postValue(UiState(error = error))
            }, { characters ->
                _uiState.postValue(UiState(characters = characters))
            })
        }
    }

    fun searchCharactersByKeyword(keyWord: String) {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            searchCharactersByKeywordUseCase.invoke(keyWord).fold({ error ->
                _uiState.postValue(UiState(error = error))
            }, { characters ->
                _uiState.postValue(UiState(characters = characters))
            })
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val characters: List<CharactersFeed>? = null,
        val error: ErrorApp? = null
    )
}