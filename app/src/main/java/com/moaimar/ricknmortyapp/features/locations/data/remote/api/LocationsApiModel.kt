package com.moaimar.ricknmortyapp.features.locations.data.remote.api

import com.google.gson.annotations.SerializedName

data class LocationsResponse(@SerializedName("results") val result: List<LocationsApiModel>)

data class LocationsApiModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("dimension") val dimension: String,
    @SerializedName("residents") val residents: List<String>

)
