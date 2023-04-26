package com.moaimar.ricknmortyapp.features.locations.data

import com.moaimar.ricknmortyapp.app.data.local.cache.AppCache
import com.moaimar.ricknmortyapp.app.data.local.cache.LOCATIONS_CACHE_KEY
import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.app.funtional.Either
import com.moaimar.ricknmortyapp.app.funtional.right
import com.moaimar.ricknmortyapp.features.locations.data.local.LocationsLocalDataRepository
import com.moaimar.ricknmortyapp.features.locations.data.remote.LocationsRemoteDataRepository
import com.moaimar.ricknmortyapp.features.locations.domain.LocationsInfo
import com.moaimar.ricknmortyapp.features.locations.domain.LocationsRepository
import javax.inject.Inject

class LocationsDataRepository @Inject constructor(
    private val localDataRepository: LocationsLocalDataRepository,
    private val remoteDataRepository: LocationsRemoteDataRepository,
    private val cache : AppCache
) : LocationsRepository {


    override suspend fun getLocationsList(): Either<ErrorApp, List<LocationsInfo>> {
        return if (cache.isCacheOutDated(LOCATIONS_CACHE_KEY)) {
            remoteDataRepository.getLocations().map { remoteList ->
                localDataRepository.delete()
                localDataRepository.save(remoteList)
                cache.saveCacheDate(LOCATIONS_CACHE_KEY)
                remoteList
            }

        } else {
            localDataRepository.getLocations().right()
        }
    }

    override suspend fun getLocationDetail(keyId: Int): Either<ErrorApp, LocationsInfo> =
        localDataRepository.getLocationsDetail(keyId)?.right()
            ?: remoteDataRepository.getLocationsInfo(keyId).map { it }

    override suspend fun refreshLocationsList(): Either<ErrorApp, List<LocationsInfo>> {
        localDataRepository.delete()
        cache.refreshCache(LOCATIONS_CACHE_KEY)
        return getLocationsList()
    }

    override suspend fun searchLocationsByKeyword(keyWord: String): Either<ErrorApp, List<LocationsInfo>> =
        remoteDataRepository.searchLocationsByKeyword(keyWord)


}