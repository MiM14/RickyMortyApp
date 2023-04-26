package com.moaimar.ricknmortyapp.features.locations.domain

import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.app.funtional.Either
import javax.inject.Inject

class GetLocationsDetailUseCase @Inject constructor(private val repository: LocationsRepository) {
    suspend operator fun invoke(keyId: Int): Either<ErrorApp, LocationsInfo> =
        repository.getLocationDetail(keyId)


}