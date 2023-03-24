package com.moaimar.ricknmortyapp.features.characterslist.data.local.db

import com.moaimar.ricknmortyapp.app.domain.ErrorApp
import com.moaimar.ricknmortyapp.app.funtional.Either
import com.moaimar.ricknmortyapp.app.funtional.left
import com.moaimar.ricknmortyapp.app.funtional.right
import com.moaimar.ricknmortyapp.features.characterslist.data.local.CharacterListLocalDataRepository
import com.moaimar.ricknmortyapp.features.characterslist.domain.CharacterInfo
import javax.inject.Inject

class CharacterListLocalDataSource @Inject constructor(private val characterDao: CharacterDao) :
    CharacterListLocalDataRepository {
    override fun getCharacters(): List<CharacterInfo> =
        characterDao.getCharacters().map {
            it.toDomain()
        }


    override fun getCharactersInfo(keyId: Int): CharacterInfo?{
        return characterDao.getCharactersInfo(keyId)?.toDomain()
    }

    override fun save(characters: List<CharacterInfo>) {
        val listOfCharacters = characters.map { it.toEntity() }
        characterDao.save(*listOfCharacters.toTypedArray())
    }

    override fun delete() {
        characterDao.delete()
    }


}