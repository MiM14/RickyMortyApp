package com.moaimar.ricknmortyapp.features.locations.data.remote

import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.app.funtional.Either
import com.moaimar.ricknmortyapp.features.locations.domain.LocationsInfo

interface LocationsRemoteDataRepository {

    suspend fun getLocations(): Either<ErrorApp, List<LocationsInfo>>

    suspend fun getLocationsInfo(keyId: Int): Either<ErrorApp, LocationsInfo>

    suspend fun searchLocationsByKeyword(keyWord: String): Either<ErrorApp, List<LocationsInfo>>

}