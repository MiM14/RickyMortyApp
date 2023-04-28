package com.moaimar.ricknmortyapp.features.locations.data.remote.api

import com.moaimar.ricknmortyapp.features.locations.domain.LocationsInfo
import com.moaimar.ricknmortyapp.features.locations.domain.Resident

fun LocationsApiModel.toDomain() =
    LocationsInfo(
        this.id,
        this.name,
        this.type,
        this.dimension,
        getResidents(this.residents)
    )

private fun getResidents(residentUrls: List<String>): List<Resident> =
    residentUrls.map { residentUrl ->
        val id = residentUrl.substringAfterLast("/")
        Resident(id.toInt(), "https://rickandmortyapi.com/api/character/avatar/$id.jpeg")
    }
