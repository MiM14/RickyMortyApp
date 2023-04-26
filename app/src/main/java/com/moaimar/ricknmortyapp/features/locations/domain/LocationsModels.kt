package com.moaimar.ricknmortyapp.features.locations.domain

data class LocationsInfo(
    val id: Int,
    val name: String,
    val type : String,
    val dimension: String,
    val images: List<String>
    )

data class LocationsFeed(
    val id: Int,
    val name: String,
    val type : String,
    val dimension: String
)