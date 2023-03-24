package com.moaimar.ricknmortyapp.features.characterslist.data.remote.api

import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.app.funtional.Either
import com.moaimar.ricknmortyapp.app.funtional.left
import com.moaimar.ricknmortyapp.app.funtional.right
import com.moaimar.ricknmortyapp.features.characterslist.data.remote.CharacterListRemoteDataRepository
import com.moaimar.ricknmortyapp.features.characterslist.domain.CharacterInfo
import javax.inject.Inject

class CharacterListRemoteDataSource @Inject constructor(private val apiClient: ApiEndPoints) :
    CharacterListRemoteDataRepository {

    override suspend fun getCharacters(): Either<ErrorApp, List<CharacterInfo>> {
        val apiCall = apiClient.getCharacters()
        return apiCall.body()?.let {
            it.result.map { character ->
                character.toDomain()
            }.right()
        } ?: run {
            ErrorApp.DataLayerError.left()
        }
    }

    override suspend fun getCharactersInfo(keyId: Int): Either<ErrorApp, CharacterInfo> {
        val apiCall = apiClient.getCharactersInfo(keyId)
        return apiCall.body()?.let {
            it.toDomain().right()
        } ?: run {
            ErrorApp.DataLayerError.left()
        }
    }

}