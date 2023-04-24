package com.moaimar.ricknmortyapp.features.locations.data.remote.api

import com.moaimar.ricknmortyapp.features.locations.domain.LocationsInfo

fun LocationsApiModel.toDomain() =
    LocationsInfo(
        this.id,
        this.name,
        this.type,
        this.dimension,
        getImages(this.residents)
    )

private fun getImages(residentUrls: List<String>): List<String> {
    val images = mutableListOf<String>()
    residentUrls.forEach {residentUrl ->
        val id = residentUrl.substringAfterLast("/")
        images.add("https://rickandmortyapi.com/api/character/avatar/$id.jpeg")
    }
    return images

}