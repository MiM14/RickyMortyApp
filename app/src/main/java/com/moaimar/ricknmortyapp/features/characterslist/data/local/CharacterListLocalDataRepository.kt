package com.moaimar.ricknmortyapp.features.characterslist.data.local

import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.app.funtional.Either
import com.moaimar.ricknmortyapp.features.characterslist.domain.CharacterInfo

interface CharacterListLocalDataRepository {
    fun getCharacters(): List<CharacterInfo>
    fun getCharactersInfo(keyId: Int):  CharacterInfo?
    fun save(characters: List<CharacterInfo>)
    fun delete()
}