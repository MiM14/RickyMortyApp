package com.moaimar.ricknmortyapp.features.characterslist.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndPoints {

    @GET("character")
    suspend fun getCharacters(): Response<ApiModel>

    @GET("character/{id}")
    suspend fun getCharactersInfo(@Path("id") keyId: Int): Response<CharacterApiModel>

    @GET("character")
    suspend fun searchCharactersByKeyword(@Query("name") keyWord: String): Response<ApiModel>
}