package com.moaimar.ricknmortyapp.features.characterslist.data.local.db

import com.moaimar.ricknmortyapp.features.characterslist.domain.CharacterInfo

fun CharacterEntity.toDomain() =
    CharacterInfo(this.id, this.name, this.status, this.species, this.gender, this.urlImage)

fun CharacterInfo.toEntity() =
    CharacterEntity(this.id, this.name, this.status, this.species, this.gender, this.urlImage)