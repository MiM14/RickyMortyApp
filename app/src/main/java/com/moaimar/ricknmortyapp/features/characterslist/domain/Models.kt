package com.moaimar.ricknmortyapp.features.characterslist.domain

data class CharacterInfo(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val urlImage: String
)
