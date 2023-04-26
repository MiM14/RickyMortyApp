package com.moaimar.ricknmortyapp.features.locations.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationsApiEndPoints {

    @GET("location")
    suspend fun geLocations(): Response<LocationsResponse>

    @GET("location/{id}")
    suspend fun getLocationsInfo(@Path("id") keyId: Int): Response<LocationsApiModel>

    @GET("location")
    suspend fun searchLocationsByKeyword(@Query("name") keyWord: String): Response<LocationsResponse>
}