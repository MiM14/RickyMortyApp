package com.moaimar.ricknmortyapp.features.locations.di

import com.moaimar.ricknmortyapp.features.locations.data.LocationsDataRepository
import com.moaimar.ricknmortyapp.features.locations.data.local.LocationsLocalDataRepository
import com.moaimar.ricknmortyapp.features.locations.data.local.db.LocationsLocalDataSource
import com.moaimar.ricknmortyapp.features.locations.data.remote.LocationsRemoteDataRepository
import com.moaimar.ricknmortyapp.features.locations.data.remote.api.LocationsRemoteDataSource
import com.moaimar.ricknmortyapp.features.locations.domain.LocationsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LocationsModule {

    @Binds
    abstract fun bindLocationsRepository(repository: LocationsDataRepository): LocationsRepository

    @Binds
    abstract fun bindLocationsLocalRepository(repository: LocationsLocalDataSource): LocationsLocalDataRepository

    @Binds
    abstract fun bindLocationsRemoteRepository(repository: LocationsRemoteDataSource): LocationsRemoteDataRepository

}