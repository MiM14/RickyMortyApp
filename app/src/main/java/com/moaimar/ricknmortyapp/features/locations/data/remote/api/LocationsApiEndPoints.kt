package com.moaimar.ricknmortyapp.features.locations.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationsApiEndPoints {

    @GET("locations")
    suspend fun geLocations(): Response<LocationsResponse>

    @GET("locations/{id}")
    suspend fun getLocationsInfo(@Path("id") keyId: Int): Response<LocationsApiModel>

    @GET("locations")
    suspend fun searchLocationsByKeyword(@Query("name") keyWord: String): Response<LocationsResponse>
}