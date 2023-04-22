    package com.moaimar.ricknmortyapp.features.characterslist.domain

fun CharacterInfo.toFeed()=
    CharactersFeed(
        this.id,
        this.name,
        this.urlImage
    )
