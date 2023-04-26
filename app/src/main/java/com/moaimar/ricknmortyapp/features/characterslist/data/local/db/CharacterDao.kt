package com.moaimar.ricknmortyapp.features.characterslist.data.local.db

import androidx.room.*

@Dao
interface CharacterDao {

    @Query("SELECT * FROM $CHARACTER_TABLE_NAME")
    fun getCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM $CHARACTER_TABLE_NAME WHERE id = :keyId")
    fun getCharactersInfo(keyId: Int): CharacterEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg character: CharacterEntity)

    @Query("DELETE FROM $CHARACTER_TABLE_NAME")
    fun delete()
}