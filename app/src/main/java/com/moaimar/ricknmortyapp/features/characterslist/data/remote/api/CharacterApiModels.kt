package com.moaimar.ricknmortyapp.features.characterslist.data.remote.api

import com.google.gson.annotations.SerializedName

data class CharacterResponse(@SerializedName("results") val result: List<CharacterApiModel>)
data class CharacterApiModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("image") val urlImage: String
)

