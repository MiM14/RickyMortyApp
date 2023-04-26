package com.moaimar.ricknmortyapp.features.locations.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocationsDao {

    @Query("SELECT * FROM $LOCATIONS_TABLE_NAME")
    fun getLocations(): List<LocationsEntity>

    @Query("SELECT * FROM $LOCATIONS_TABLE_NAME WHERE id = :keyId ")
    fun getLocationsInfo(keyId: Int): LocationsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg location: LocationsEntity)

    @Query("DELETE FROM $LOCATIONS_TABLE_NAME")
    fun delete()
}