package com.moaimar.ricknmortyapp.features.characterslist.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiEndPoints {

    @GET("character")
    suspend fun getCharacters(): Response<ApiModel>

    @GET("character/{id}")
    suspend fun getCharactersInfo(@Path("id") keyId: Int): Response<CharacterApiModel>
}