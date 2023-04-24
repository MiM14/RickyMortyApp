package com.moaimar.ricknmortyapp.features.locations.data.remote.api

import com.moaimar.ricknmortyapp.app.data.remote.apiCall
import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.app.funtional.Either
import com.moaimar.ricknmortyapp.features.locations.data.remote.LocationsRemoteDataRepository
import com.moaimar.ricknmortyapp.features.locations.domain.LocationsInfo
import javax.inject.Inject

class LocationsRemoteDataSource @Inject constructor(private val apiClient: LocationsApiEndPoints) :
    LocationsRemoteDataRepository {

    override suspend fun getLocations(): Either<ErrorApp, List<LocationsInfo>> {
        return apiCall {
            apiClient.geLocations()
        }.map { apiModel ->
            apiModel.result.map { locations ->
                locations.toDomain()
            }
        }
    }

    override suspend fun getLocationsInfo(keyId: Int): Either<ErrorApp, LocationsInfo> {
        return apiCall {
            apiClient.getLocationsInfo(keyId)
        }.map { location ->
            location.toDomain()
        }
    }

    override suspend fun searchLocationsByKeyword(keyWord: String): Either<ErrorApp, List<LocationsInfo>> {
        return apiCall {
            apiClient.searchLocationsByKeyword(keyWord)
        }.map { apiModel ->
            apiModel.result.map { locations ->
                locations.toDomain()
            }
        }
    }
}