package com.moaimar.ricknmortyapp.features.locations.data.local.db

import com.moaimar.ricknmortyapp.features.locations.domain.LocationsInfo

fun LocationsEntity.toDomain() =
    LocationsInfo(
        this.id,
        this.name,
        this.type,
        this.dimension,
        this.images
    )

fun LocationsInfo.toEntity() =
    LocationsEntity(
        this.id,
        this.name,
        this.type,
        this.dimension,
        this.images
    )