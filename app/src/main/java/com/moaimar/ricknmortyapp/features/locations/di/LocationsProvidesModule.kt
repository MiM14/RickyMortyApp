package com.moaimar.ricknmortyapp.features.locations.di

import com.moaimar.ricknmortyapp.app.data.local.db.RnMDatabase
import com.moaimar.ricknmortyapp.features.locations.data.local.db.LocationsDao
import com.moaimar.ricknmortyapp.features.locations.data.remote.api.LocationsApiEndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationsProvidesModule {

    @Provides
    @Singleton
    fun provideLocationsDao(rnMDatabase: RnMDatabase): LocationsDao {
        return rnMDatabase.locationsDao()
    }

    @Provides
    @Singleton
    fun provideLocationsApiEndPoint(retrofit: Retrofit): LocationsApiEndPoints {
        return retrofit.create(LocationsApiEndPoints::class.java)
    }
}