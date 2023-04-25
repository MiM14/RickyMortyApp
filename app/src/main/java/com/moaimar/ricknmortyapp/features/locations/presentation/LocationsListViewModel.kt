package com.moaimar.ricknmortyapp.features.locations.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.features.characterslist.domain.CharactersFeed
import com.moaimar.ricknmortyapp.features.locations.domain.GetLocationsListUseCase
import com.moaimar.ricknmortyapp.features.locations.domain.LocationsFeed
import com.moaimar.ricknmortyapp.features.locations.domain.RefreshLocationsListUseCase
import com.moaimar.ricknmortyapp.features.locations.domain.SearchLocationsByKeywordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsListViewModel @Inject constructor(
    private val getLocationsListUseCase: GetLocationsListUseCase,
    private val refreshLocationsListUseCase: RefreshLocationsListUseCase,
    private val searchLocationsByKeywordUseCase: SearchLocationsByKeywordUseCase
) : ViewModel() {
    private val _uiState : MutableLiveData<UiState> = MutableLiveData()
    val uiState : LiveData<UiState> = _uiState

    fun getLocations(){
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            getLocationsListUseCase.invoke().fold({ errorApp ->
                _uiState.postValue(
                    UiState(isLoading = false, error = errorApp)
                )
            },{ locationsFeed ->
                _uiState.postValue(
                    UiState(isLoading = false, locations = locationsFeed)
                )
            })
        }
    }

    fun refreshFeed(){
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            refreshLocationsListUseCase.invoke().fold({ errorApp ->
                _uiState.postValue(
                    UiState(isLoading = false, error = errorApp)
                )
            },{ locationsFeed ->
                _uiState.postValue(
                    UiState(isLoading = false, locations = locationsFeed)
                )
            })
        }
    }

    fun searchLocationByKeyWord(keyWord: String){
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            searchLocationsByKeywordUseCase.invoke(keyWord).fold({ errorApp ->
                _uiState.postValue(
                    UiState(isLoading = false, error = errorApp)
                )
            },{ locationsFeed ->
                _uiState.postValue(
                    UiState(isLoading = false, locations = locationsFeed)
                )
            })
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val locations: List<LocationsFeed>? = null,
        val error: ErrorApp? = null
    )
}