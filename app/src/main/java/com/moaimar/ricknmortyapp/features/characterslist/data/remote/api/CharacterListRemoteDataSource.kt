package com.moaimar.ricknmortyapp.features.characterslist.data.remote.api

import com.moaimar.ricknmortyapp.app.data.remote.apiCall
import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.app.funtional.Either
import com.moaimar.ricknmortyapp.features.characterslist.data.remote.CharacterListRemoteDataRepository
import com.moaimar.ricknmortyapp.features.characterslist.domain.CharacterInfo
import javax.inject.Inject

class CharacterListRemoteDataSource @Inject constructor(private val apiClient: ApiEndPoints) :
    CharacterListRemoteDataRepository {

    override suspend fun getCharacters(): Either<ErrorApp, List<CharacterInfo>> {
        return apiCall {
            apiClient.getCharacters()
        }.map { apiModel ->
            apiModel.result.map { character ->
                character.toDomain()
            }
        }
    }

    override suspend fun getCharactersInfo(keyId: Int): Either<ErrorApp, CharacterInfo> {
        return apiCall {
            apiClient.getCharactersInfo(keyId)
        }.map { character ->
            character.toDomain()
        }
    }

}