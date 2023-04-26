package com.moaimar.ricknmortyapp.features.locations.data.local.db

import com.moaimar.ricknmortyapp.features.locations.data.local.LocationsLocalDataRepository
import com.moaimar.ricknmortyapp.features.locations.domain.LocationsInfo
import javax.inject.Inject

class LocationsLocalDataSource @Inject constructor(private val locationsDao: LocationsDao) :
    LocationsLocalDataRepository {
    override fun getLocations(): List<LocationsInfo> =
        locationsDao.getLocations().map {
            it.toDomain()
        }


    override fun getLocationsDetail(keyId: Int): LocationsInfo? =
        locationsDao.getLocationsInfo(keyId)?.toDomain()


    override fun save(locations: List<LocationsInfo>) {
        val listOfLocations = locations.map { it.toEntity() }
        locationsDao.save(*listOfLocations.toTypedArray())
    }

    override fun delete() {
        locationsDao.delete()
    }
}