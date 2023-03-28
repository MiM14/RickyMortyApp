package com.moaimar.ricknmortyapp.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moaimar.ricknmortyapp.BuildConfig
import com.moaimar.ricknmortyapp.features.characterslist.data.local.db.CharacterDao
import com.moaimar.ricknmortyapp.features.characterslist.data.local.db.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
abstract class RnMDatabase(): RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}