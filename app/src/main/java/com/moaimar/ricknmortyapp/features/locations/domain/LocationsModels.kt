package com.moaimar.ricknmortyapp.features.locations.domain

data class LocationsInfo(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<Resident>
)

data class LocationsFeed(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String
)

data class Resident(val id: Int, val image:String)