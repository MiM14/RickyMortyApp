package com.moaimar.ricknmortyapp.features.locations.data.local

import com.moaimar.ricknmortyapp.features.locations.domain.LocationsInfo

interface LocationsLocalDataRepository {

    fun getLocations(): List<LocationsInfo>
    fun getLocationsDetail(keyId: Int): LocationsInfo?
    fun save(locations: List<LocationsInfo>)
    fun delete()

}