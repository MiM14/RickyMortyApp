package com.moaimar.ricknmortyapp.features.characterslist.data.remote.api

import com.moaimar.ricknmortyapp.app.di.RemoteModule.CHARACTER_END_POINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndPoints {

    @GET(CHARACTER_END_POINT)
    suspend fun getCharacters(
        @Query("page") page:Int
    ): Response<ApiModel>

    @GET("character/{id}")
    suspend fun getCharactersInfo(@Path("id") keyId: Int): Response<CharacterApiModel>
}