package com.moaimar.ricknmortyapp.features.characterslist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.features.characterslist.domain.CharacterInfo
import com.moaimar.ricknmortyapp.features.characterslist.domain.GetCharacterDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val getCharacterDetailUseCase: GetCharacterDetailUseCase) :
    ViewModel() {

    private val _uiState: MutableLiveData<UiState> = MutableLiveData()
    val uiState: LiveData<UiState> = _uiState

    fun getCharacterDetail(keyId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getCharacterDetailUseCase.invoke(keyId).fold({ error ->
                _uiState.postValue(
                    UiState(
                        error = error
                    )
                )
            }, { character ->
                _uiState.postValue(
                    UiState(character = character)
                )
            })
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val character: CharacterInfo? = null,
        val error: ErrorApp? = null
    )
}