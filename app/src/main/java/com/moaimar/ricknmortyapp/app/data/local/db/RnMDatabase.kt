package com.moaimar.ricknmortyapp.app.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.moaimar.ricknmortyapp.BuildConfig
import com.moaimar.ricknmortyapp.features.characterslist.data.local.db.CharacterDao
import com.moaimar.ricknmortyapp.features.characterslist.data.local.db.CharacterEntity
import com.moaimar.ricknmortyapp.features.locations.data.local.db.LocationsDao
import com.moaimar.ricknmortyapp.features.locations.data.local.db.LocationsEntity

@Database(
    entities = [CharacterEntity::class, LocationsEntity::class],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
@TypeConverters(ResidentsConverter::class)
abstract class RnMDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun locationsDao(): LocationsDao
}