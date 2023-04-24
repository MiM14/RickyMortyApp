package com.moaimar.ricknmortyapp.features.locations.domain

import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.app.funtional.Either

interface LocationsRepository {

    suspend fun getLocationsList(): Either<ErrorApp, List<LocationsInfo>>

    suspend fun getLocationDetail(keyId: Int): Either<ErrorApp, LocationsInfo>

    suspend fun refreshLocationsList(): Either<ErrorApp, List<LocationsInfo>>

    suspend fun searchLocationsByKeyword(keyWord: String): Either<ErrorApp, List<LocationsInfo>>
}