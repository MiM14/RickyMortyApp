package com.moaimar.ricknmortyapp.features.locations.domain

fun LocationsInfo.toFeed() =
    LocationsFeed(
        this.id,
        this.name,
        this.type,
        this.dimension
    )


