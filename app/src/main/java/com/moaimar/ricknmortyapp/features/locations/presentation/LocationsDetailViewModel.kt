package com.moaimar.ricknmortyapp.features.locations.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.features.locations.domain.GetLocationsDetailUseCase
import com.moaimar.ricknmortyapp.features.locations.domain.LocationsInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsDetailViewModel @Inject constructor(private val getLocationsDetail: GetLocationsDetailUseCase) :
    ViewModel() {

    private val _uiState: MutableLiveData<UiState> = MutableLiveData()
    val uiState: LiveData<UiState> = _uiState

    fun getLocationDetail(keyId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getLocationsDetail.invoke(keyId).fold({ errorApp ->
                _uiState.postValue(
                    UiState(
                        error = errorApp
                    )
                )
            }, { location ->
                _uiState.postValue(
                    UiState(location = location)
                )
            })
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val location: LocationsInfo? = null,
        val error: ErrorApp? = null
    )
}