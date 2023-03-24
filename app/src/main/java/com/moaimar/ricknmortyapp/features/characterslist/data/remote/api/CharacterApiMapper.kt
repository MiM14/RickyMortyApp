package com.moaimar.ricknmortyapp.features.characterslist.data.remote.api

import com.moaimar.ricknmortyapp.features.characterslist.domain.CharacterInfo

fun CharacterApiModel.toDomain()= CharacterInfo(
    this.id,
    this.name,
    this.status,
    this.species,
    this.gender,
    this.urlImage,
)