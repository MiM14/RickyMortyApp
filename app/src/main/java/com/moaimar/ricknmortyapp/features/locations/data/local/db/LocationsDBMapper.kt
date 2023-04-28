package com.moaimar.ricknmortyapp.features.locations.data.local.db

import com.moaimar.ricknmortyapp.features.locations.domain.LocationsInfo
import com.moaimar.ricknmortyapp.features.locations.domain.Resident

fun LocationsEntity.toDomain() =
    LocationsInfo(
        this.id,
        this.name,
        this.type,
        this.dimension,
        this.residents.map{
            it.toDomain()
        }
    )

fun LocationsInfo.toEntity() =
    LocationsEntity(
        this.id,
        this.name,
        this.type,
        this.dimension,
        this.residents.map {
            it.toEntity()
        }
    )

fun ResidentEntity.toDomain() =
    Resident(
        this.id,
        this.image
    )

fun Resident.toEntity() =
    ResidentEntity(
        this.id,
        this.image
    )